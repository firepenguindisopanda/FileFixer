package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StandardRename {

    private static final Logger logger = LoggerFactory.getLogger(StandardRename.class);

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

            if (!(parts[0].contains("14") && parts[0].contains("-") && parts[0].contains("60"))) continue;

            boolean matched = false;
            for (Student student : students) {
                if (!(student.getName().contains(parts[1])
                    && student.getName().contains(parts[2])
                    && fileName.contains(student.getPID()))) continue;

                String assignmentPart = parts[parts.length - 1];

                int num = parts.length - 2;
                while (num >= 0 && parts[num].equals(student.getPID())) {
                    assignmentPart = parts[num] + "_" + assignmentPart;
                    num--;
                }

                num = parts.length - 2;
                while (num >= 0 && !parts[num].equals(student.getPID())) {
                    assignmentPart = parts[num] + "_" + assignmentPart;
                    num--;
                }

                assignmentPart = assignmentPart.replace(student.getPID() + "_", "");

                String newFileName = student.getName() + "_" + student.getPID()
                    + "_assignsubmission_file_" + assignmentPart + ".pdf";

                Path source = baseDir.resolve(fileName);
                Path target = outputDir.resolve(newFileName);

                try {
                    Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    student.setAttendance(false);
                    matched = true;
                    logger.info("Renamed (standard): {} -> {}", fileName, newFileName);
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
