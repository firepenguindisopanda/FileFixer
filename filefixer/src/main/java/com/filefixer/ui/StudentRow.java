package com.filefixer.ui;

import com.filefixer.model.RenameStatus;
import com.filefixer.model.Student;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentRow {

    private final StringProperty name;
    private final StringProperty pid;
    private final StringProperty id;
    private final StringProperty status;
    private final StringProperty location;
    private final StringProperty renamedFile;

    public StudentRow(Student student) {
        this.name = new SimpleStringProperty(student.getName());
        this.pid = new SimpleStringProperty(student.getPID());
        this.id = new SimpleStringProperty(student.getID());
        this.status = new SimpleStringProperty("Pending");
        this.location = new SimpleStringProperty("");
        this.renamedFile = new SimpleStringProperty("");
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty pidProperty() { return pid; }
    public StringProperty idProperty() { return id; }
    public StringProperty statusProperty() { return status; }
    public StringProperty locationProperty() { return location; }
    public StringProperty renamedFileProperty() { return renamedFile; }

    public String getName() { return name.get(); }
    public String getPid() { return pid.get(); }
    public String getId() { return id.get(); }
    public String getStatus() { return status.get(); }
    public String getLocation() { return location.get(); }
    public String getRenamedFile() { return renamedFile.get(); }

    public void markFound(String newFileName, String sourcePath) {
        status.set("Found");
        renamedFile.set(newFileName);
        location.set(sourcePath);
    }

    public void markMissing() {
        status.set("Missing");
    }

    public void markReview(String reason) {
        status.set("Review");
        renamedFile.set(reason);
    }

    public RenameStatus getStatusEnum() {
        return switch (status.get()) {
            case "Found" -> RenameStatus.FOUND;
            case "Missing" -> RenameStatus.MISSING;
            case "Review" -> RenameStatus.REVIEW;
            default -> RenameStatus.MISSING;
        };
    }
}
