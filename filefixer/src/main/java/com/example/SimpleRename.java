package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SimpleRename {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRename.class);

    static void startRenamingProcess(List<Student> students, FileCollection fileCollection,
                                      Path outputDir, List<String> invalidSubmissions) {
        try {
            Files.createDirectories(outputDir);
        } catch (IOException e) {
            logger.error("Failed to create output directory: {}", outputDir);
            return;
        }

        Path baseDir = outputDir.getParent();

        for (String fileName : fileCollection) {
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
                    Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    student.setAttendance(false);
                    matched = true;
                    logger.info("Renamed (simple): {} -> {}", fileName, newFileName);
                } catch (IOException e) {
                    logger.error("Failed to copy {}: {}", fileName, e.getMessage());
                }
                break;
            }

            if (!matched && fileName.toLowerCase().endsWith(".pdf")) {
                invalidSubmissions.add("Problem submission to review: " + fileName);
            }
        }
    }
}
