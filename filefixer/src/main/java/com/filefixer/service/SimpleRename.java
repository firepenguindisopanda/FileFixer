package com.filefixer.service;

import com.filefixer.model.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class SimpleRename {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRename.class);

    static void startRenamingProcess(List<Student> students, FileCollection fileCollection,
                                      Path outputDir, List<String> invalidSubmissions,
                                      RenameProgress progress) {
        try {
            Files.createDirectories(outputDir);
        } catch (IOException e) {
            logger.error("Failed to create output directory: {}", outputDir);
            return;
        }

        Path baseDir = outputDir.getParent();
        List<String> files = fileCollection.iterator().hasNext()
            ? new java.util.ArrayList<>(java.util.stream.StreamSupport.stream(fileCollection.spliterator(), false).toList())
            : List.of();

        for (int i = 0; i < files.size(); i++) {
            String fileName = files.get(i);
            if (fileName.contains(".csv")) continue;

            String[] parts = fileName.split("_");
            if (parts.length < 3) continue;

            if (!parts[2].equals("assignsubmission")) continue;

            boolean matched = false;
            for (Student student : students) {
                if (!parts[0].equals(student.getName())) continue;

                String assignmentPart = parts.length >= 6
                    ? parts[4] + "_" + parts[5]
                    : parts[4];

                String newFileName = student.getName() + "_" + student.getPID()
                    + "_assignsubmission_file_" + assignmentPart + ".pdf";

                Path source = baseDir.resolve(fileName);
                Path target = outputDir.resolve(newFileName);

                try {
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    student.setAttendance(false);
                    matched = true;
                    progress.onFileProcessed(fileName, student, true, newFileName);
                    logger.info("Renamed (simple): {} -> {}", fileName, newFileName);
                } catch (IOException e) {
                    logger.error("Failed to copy {}: {}", fileName, e.getMessage());
                    progress.onFileProcessed(fileName, student, false, null);
                }
                break;
            }

            if (!matched && fileName.toLowerCase().endsWith(".pdf")) {
                invalidSubmissions.add("Problem submission to review: " + fileName);
                progress.onFileProcessed(fileName, null, false, null);
            }

            progress.onProgress(i + 1, files.size());
        }
    }
}
