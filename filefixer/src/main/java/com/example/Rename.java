package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Rename {

    private static final Logger logger = LoggerFactory.getLogger(Rename.class);
    private final Path outputDir;
    private final List<String> invalidSubmissions = new ArrayList<>();

    public Rename(Path outputDir) {
        this.outputDir = outputDir;
    }

    public void startRename(List<Student> students, ToRename toRename, FileCollection fileCollection) {
        fileCollection.setFiles(toRename.getToBeRenamedList());

        SimpleRename.startRenamingProcess(students, fileCollection, outputDir, invalidSubmissions);
        StandardRename.startRenamingProcess(students, fileCollection, outputDir, invalidSubmissions);
        ComplexRename.startRenamingProcess(students, fileCollection, outputDir, invalidSubmissions);

        generateReport(students);
    }

    private void generateReport(List<Student> students) {
        Path reportPath = outputDir.resolveSibling("missingSubmissions.txt");

        try {
            List<String> lines = new ArrayList<>();

            List<String> unmatchedInvalid = new ArrayList<>(invalidSubmissions);
            for (Student student : students) {
                unmatchedInvalid.removeIf(invalid ->
                    invalid.contains(student.getName())
                    || invalid.contains(student.getID())
                    || invalid.contains(student.getName().toLowerCase())
                    || invalid.contains(student.getName().toUpperCase())
                    || invalid.contains(student.getName().replace(" ", ""))
                );
            }

            for (String invalid : unmatchedInvalid) {
                if (!invalid.isBlank()) {
                    lines.add(invalid);
                }
            }

            for (Student student : students) {
                if (student.getAttendanceStatus()) {
                    lines.add("Submission missing: " + student.getName() + " " + student.getID());
                }
            }

            Files.write(reportPath, lines);
            logger.info("Report written to {}", reportPath);
        } catch (IOException e) {
            logger.error("Failed to write report: {}", e.getMessage());
        }
    }

    public static void copyFiles(Path originalFile, Path newFile) {
        try {
            Files.copy(originalFile, newFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error("Failed to copy {} to {}: {}", originalFile, newFile, e.getMessage());
        }
    }
}
