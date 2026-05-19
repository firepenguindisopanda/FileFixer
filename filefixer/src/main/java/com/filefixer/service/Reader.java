package com.filefixer.service;

import com.filefixer.exception.FileFixerException;
import com.filefixer.model.Student;
import com.filefixer.model.ToRename;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private static final Logger logger = LoggerFactory.getLogger(Reader.class);
    private final Path baseDir;

    public Reader() {
        this(Paths.get(System.getProperty("user.dir"), "filesToRename"));
    }

    public Reader(Path baseDir) {
        this.baseDir = baseDir;
    }

    public List<Student> loadCsvData() throws FileFixerException {
        ensureDirectoryExists();

        List<Path> csvFiles = findCsvFiles();
        if (csvFiles.isEmpty()) {
            throw new FileFixerException("No CSV files found in the filesToRename folder");
        }
        if (csvFiles.size() > 1) {
            throw new FileFixerException("Multiple CSV files found. Please place only 1 CSV file in the folder");
        }

        Path csvPath = csvFiles.get(0);
        logger.info("Reading CSV: {}", csvPath.getFileName());

        List<Student> students = new ArrayList<>();
        try (var lines = Files.lines(csvPath)) {
            boolean headerSkipped = false;
            int lineNum = 0;
            for (String line : lines.toList()) {
                lineNum++;
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                if (line.isBlank()) continue;

                String[] details = line.split(",", -1);
                if (details.length < 3) {
                    logger.warn("Skipping malformed CSV row at line {}: {}", lineNum, line);
                    continue;
                }

                String[] pidParts = details[0].split("\\s+");
                if (pidParts.length < 2) {
                    logger.warn("Skipping row with invalid PID at line {}: {}", lineNum, line);
                    continue;
                }

                String pid = pidParts[1];
                String name = details[1];
                String id = details[2];

                students.add(new Student(pid, name, id, true));
            }
        } catch (IOException e) {
            throw new FileFixerException("Failed to read CSV file: " + csvPath, e);
        }

        logger.info("Loaded {} students from CSV", students.size());
        return students;
    }

    public ToRename loadDirectoryFiles() throws FileFixerException {
        ensureDirectoryExists();

        ToRename toRename = new ToRename("default", "default", "default", true);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(baseDir)) {
            for (Path entry : stream) {
                toRename.addToList(entry.getFileName().toString());
            }
        } catch (IOException e) {
            throw new FileFixerException("Failed to read directory: " + baseDir, e);
        }

        if (toRename.getFileCount() == 0) {
            throw new FileFixerException("No files found in filesToRename folder");
        }

        logger.info("Found {} files in directory", toRename.getFileCount());
        return toRename;
    }

    private void ensureDirectoryExists() throws FileFixerException {
        if (!Files.exists(baseDir)) {
            try {
                Files.createDirectories(baseDir);
            } catch (IOException e) {
                throw new FileFixerException("Failed to create directory: " + baseDir, e);
            }
        }
        if (!Files.isDirectory(baseDir)) {
            throw new FileFixerException("Path is not a directory: " + baseDir);
        }
    }

    private List<Path> findCsvFiles() throws FileFixerException {
        List<Path> csvFiles = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(baseDir)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry) && entry.getFileName().toString().toLowerCase().endsWith(".csv")) {
                    csvFiles.add(entry);
                }
            }
        } catch (IOException e) {
            throw new FileFixerException("Failed to scan directory for CSV files", e);
        }
        return csvFiles;
    }

    public Path getBaseDir() {
        return baseDir;
    }
}
