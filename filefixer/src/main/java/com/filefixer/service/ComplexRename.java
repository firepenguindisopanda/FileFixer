package com.filefixer.service;

import com.filefixer.model.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ComplexRename {

    private static final Logger logger = LoggerFactory.getLogger(ComplexRename.class);

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
        List<String> files = new java.util.ArrayList<>(java.util.stream.StreamSupport.stream(fileCollection.spliterator(), false).toList());

        for (int i = 0; i < files.size(); i++) {
            String fileName = files.get(i);
            if (fileName.contains(".csv")) continue;

            String[] spaceSplit = fileName.split(" ");
            if (spaceSplit.length == 0) continue;

            if (fileName.contains("assignsubmission")) continue;
            if (spaceSplit[0].contains("-60")) continue;

            boolean matched = false;

            for (Student student : students) {
                if (!student.getAttendanceStatus()) continue;

                String[] nameSplit = student.getName().split(" ");
                boolean nameMatch = fileName.contains(student.getID())
                    || fileName.contains(student.getName())
                    || fileName.contains(student.getName().toUpperCase())
                    || fileName.contains(student.getName().toLowerCase())
                    || (nameSplit.length >= 2
                        && fileName.contains(nameSplit[0])
                        && fileName.contains(nameSplit[1]));

                if (!nameMatch) continue;

                String nameOfFile = extractNameOfFile(fileName, student);
                if (nameOfFile == null) continue;

                nameOfFile = nameOfFile.replace(".pdf", "").replace("()", "").trim();
                if (!nameOfFile.endsWith(".pdf")) {
                    nameOfFile = nameOfFile + ".pdf";
                }

                String newFileName = student.getName() + "_" + student.getPID()
                    + "_assignsubmission_file_" + nameOfFile;

                Path source = baseDir.resolve(fileName);
                Path target = outputDir.resolve(newFileName);

                try {
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    student.setAttendance(false);
                    matched = true;
                    progress.onFileProcessed(fileName, student, true, newFileName);
                    logger.info("Renamed (complex): {} -> {}", fileName, newFileName);
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

    private static String extractNameOfFile(String fileName, Student student) {
        String nameOfFile = "";

        if (fileName.contains(student.getName()) && !fileName.contains(student.getID())) {
            String[] split = fileName.split(student.getName(), -1);
            for (String part : split) {
                if (!part.contains(student.getID())) {
                    nameOfFile += part;
                }
            }
            return nameOfFile;
        }

        if (!fileName.contains(student.getName())
            && !fileName.contains(student.getName().toUpperCase())
            && !fileName.contains(student.getName().toLowerCase())) {
            String[] split = fileName.split(student.getID(), -1);
            for (String part : split) {
                if (!part.contains(student.getID()) && !part.contains(student.getName())) {
                    nameOfFile += part;
                }
            }
            return nameOfFile;
        }

        if (!fileName.contains(student.getName()) && fileName.contains(student.getName().toUpperCase())) {
            String[] split = fileName.split(student.getName().toUpperCase(), -1);
            for (String part : split) {
                if (!part.contains(student.getID()) && !part.contains(student.getName().toUpperCase())) {
                    nameOfFile += part;
                }
            }
            return nameOfFile;
        }

        if (!fileName.contains(student.getName()) && fileName.contains(student.getName().toLowerCase())) {
            String[] split = fileName.split(student.getName().toLowerCase(), -1);
            for (String part : split) {
                if (!part.contains(student.getID()) && !part.contains(student.getName().toLowerCase())) {
                    nameOfFile += part;
                }
            }
            return nameOfFile;
        }

        if (!fileName.contains(student.getName()) && !fileName.contains(student.getID())) {
            String noSpaceName = student.getName().replace(" ", "");
            if (fileName.contains(noSpaceName)) {
                String[] split = fileName.split(noSpaceName, -1);
                for (String part : split) {
                    if (!part.contains(student.getID()) && !part.contains(noSpaceName)) {
                        nameOfFile += part;
                    }
                }
                return nameOfFile;
            }
        }

        if (fileName.contains(student.getName()) && fileName.contains(student.getID())) {
            String[] split = fileName.split(student.getName(), -1);
            for (String part : split) {
                if (!part.contains(student.getName().toUpperCase())) {
                    nameOfFile += part;
                }
            }
            if (nameOfFile.contains(student.getID())) {
                nameOfFile = nameOfFile.replace(student.getID(), "");
            }
            return nameOfFile;
        }

        return null;
    }
}
