package com.filefixer.ui;

import com.filefixer.exception.FileFixerException;
import com.filefixer.model.Student;
import com.filefixer.model.ToRename;
import com.filefixer.service.FileCollection;
import com.filefixer.service.Rename;
import com.filefixer.service.RenameProgress;
import com.filefixer.service.Reader;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainController {

    @FXML private TextField directoryField;
    @FXML private TableView<StudentRow> studentTable;
    @FXML private TableColumn<StudentRow, String> colName;
    @FXML private TableColumn<StudentRow, String> colPid;
    @FXML private TableColumn<StudentRow, String> colId;
    @FXML private TableColumn<StudentRow, String> colStatus;
    @FXML private TableColumn<StudentRow, String> colLocation;
    @FXML private TableColumn<StudentRow, String> colRenamed;
    @FXML private TextArea logArea;
    @FXML private ProgressBar progressBar;
    @FXML private Button startBtn;
    @FXML private Button openFolderBtn;
    @FXML private Button exportBtn;
    @FXML private Label statusLabel;
    @FXML private VBox logPanel;

    private final ObservableList<StudentRow> studentRows = FXCollections.observableArrayList();
    private Path outputDir;
    private Path baseDir;

    @FXML
    public void initialize() {
        colName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        colPid.setCellValueFactory(cell -> cell.getValue().pidProperty());
        colId.setCellValueFactory(cell -> cell.getValue().idProperty());
        colStatus.setCellValueFactory(cell -> cell.getValue().statusProperty());
        colLocation.setCellValueFactory(cell -> cell.getValue().locationProperty());
        colRenamed.setCellValueFactory(cell -> cell.getValue().renamedFileProperty());

        colStatus.setCellFactory(col -> new StatusCell());

        studentTable.setItems(studentRows);

        progressBar.progressProperty().bind(progressProperty);
    }

    private final javafx.beans.property.DoubleProperty progressProperty =
        new javafx.beans.property.SimpleDoubleProperty(0);

    @FXML
    private void handleBrowse() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Input Directory");
        File selected = chooser.showDialog(directoryField.getScene().getWindow());
        if (selected != null) {
            directoryField.setText(selected.getAbsolutePath());
            baseDir = selected.toPath();
            outputDir = baseDir.resolve("renamedFiles");
            loadStudents();
        }
    }

    private void loadStudents() {
        studentRows.clear();
        log("Loading CSV from: " + baseDir);

        try {
            Reader reader = new Reader(baseDir);
            List<Student> students = reader.loadCsvData();
            for (Student s : students) {
                studentRows.add(new StudentRow(s));
            }
            log("Loaded " + students.size() + " students");
            statusLabel.setText(students.size() + " students loaded. Ready to rename.");
        } catch (FileFixerException e) {
            log("Error: " + e.getMessage());
            showAlert("Load Error", e.getMessage());
        }
    }

    @FXML
    private void handleStartRename() {
        if (directoryField.getText() == null || directoryField.getText().isBlank()) {
            showAlert("No Directory", "Please select an input directory first.");
            return;
        }

        if (studentRows.isEmpty()) {
            showAlert("No Students", "No students loaded. Check your CSV file.");
            return;
        }

        startBtn.setDisable(true);
        progressBar.setProgress(0);
        logArea.clear();
        log("Starting rename process...");

        Task<Void> renameTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                List<Student> students = studentRows.stream()
                    .map(row -> new Student(row.getPid(), row.getName(), row.getId(), true))
                    .toList();

                ToRename toRename = new ToRename("default", "default", "default", true);
                try (var stream = Files.list(baseDir)) {
                    stream.filter(Files::isRegularFile)
                        .forEach(p -> toRename.addToList(p.getFileName().toString()));
                }

                FileCollection fileCollection = new FileCollection();
                Rename renameService = new Rename(outputDir);

                RenameProgress progress = new RenameProgress() {
                    @Override
                    public void onFileProcessed(String fileName, Student student, boolean success, String newFileName) {
                        Platform.runLater(() -> {
                            if (student != null) {
                                studentRows.stream()
                                    .filter(r -> r.getName().equals(student.getName()))
                                    .findFirst()
                                    .ifPresent(r -> {
                                        if (success) {
                                            r.markFound(newFileName, fileName);
                                        } else {
                                            r.markReview("Failed to process");
                                        }
                                    });
                            }
                        });
                    }

                    @Override
                    public void onProgress(int current, int total) {
                        Platform.runLater(() -> {
                            double p = (double) current / total;
                            progressBar.setProgress(p);
                        });
                    }

                    @Override
                    public void onComplete(int renamed, int missing, int review) {
                        Platform.runLater(() -> {
                            startBtn.setDisable(false);
                            openFolderBtn.setDisable(false);
                            exportBtn.setDisable(false);
                            progressBar.setProgress(1);
                            statusLabel.setText("Complete: " + renamed + " renamed, " + missing + " missing, " + review + " need review");
                            log("Rename complete. " + renamed + " files renamed successfully.");
                        });
                    }
                };

                renameService.startRename(students, toRename, fileCollection, progress);
                return null;
            }
        };

        new Thread(renameTask).start();
    }

    @FXML
    private void handleOpenOutput() {
        if (outputDir != null && Files.exists(outputDir)) {
            try {
                Desktop.getDesktop().open(outputDir.toFile());
            } catch (IOException e) {
                log("Could not open output folder: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleExportReport() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export Report");
        chooser.setInitialFileName("missingSubmissions.txt");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = chooser.showSaveDialog(logArea.getScene().getWindow());
        if (file != null) {
            try {
                Path source = outputDir.resolveSibling("missingSubmissions.txt");
                if (Files.exists(source)) {
                    Files.copy(source, file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    log("Report exported to: " + file.getAbsolutePath());
                } else {
                    Files.writeString(file.toPath(), "No missing submissions found.\n");
                    log("Empty report exported.");
                }
            } catch (IOException e) {
                log("Failed to export report: " + e.getMessage());
            }
        }
    }

    private void log(String message) {
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Platform.runLater(() -> {
            logArea.appendText("[" + timestamp + "] " + message + "\n");
            logArea.setScrollTop(Double.MAX_VALUE);
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static class StatusCell extends TableCell<StudentRow, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
                return;
            }

            Label badge = new Label(item);
            badge.getStyleClass().add("status-badge");
            badge.getStyleClass().add(switch (item) {
                case "Found" -> "status-found";
                case "Missing" -> "status-missing";
                case "Review" -> "status-review";
                default -> "status-pending";
            });

            setGraphic(badge);
            setText(null);
        }
    }
}
