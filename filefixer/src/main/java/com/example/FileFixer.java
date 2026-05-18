package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileFixer {

    private static final Logger logger = LoggerFactory.getLogger(FileFixer.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path baseDir = Paths.get(System.getProperty("user.dir"), "filesToRename");
        Path outputDir = baseDir.resolve("renamedFiles");

        try {
            System.out.println("Welcome to FileFixer!");
            System.out.println("Ensure that your CSV file and PDFs are in: " + baseDir.toAbsolutePath());
            System.out.println();
            System.out.println("Press Enter to start, type HELP for help, or type EXIT to quit.");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("HELP")) {
                printHelp(baseDir);
                input = scanner.nextLine();
            }

            while (!input.isEmpty() && !input.equalsIgnoreCase("EXIT")) {
                System.out.println("Invalid input. Press Enter to start, type HELP, or type EXIT.");
                input = scanner.nextLine();
            }

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting FileFixer.");
                return;
            }

            Reader reader = new Reader(baseDir);
            List<Student> students = reader.loadCsvData();
            ToRename toRename = reader.loadDirectoryFiles();
            FileCollection fileCollection = new FileCollection();
            Rename rename = new Rename(outputDir);

            rename.startRename(students, toRename, fileCollection);

            System.out.println("Done. Renamed files are in: " + outputDir.toAbsolutePath());
            System.out.println("Check missingSubmissions.txt for missing or problematic submissions.");

        } catch (FileFixerException e) {
            System.err.println("Error: " + e.getMessage());
            logger.error("FileFixer failed", e);
            System.exit(1);
        } finally {
            scanner.close();
        }
    }

    private static void printHelp(Path baseDir) {
        System.out.println();
        System.out.println("******** HELP ********");
        System.out.println("1. Run the program.");
        System.out.println("2. Place your PDFs and CSV file in: " + baseDir.toAbsolutePath());
        System.out.println("3. Press Enter to start.");
        System.out.println("4. Renamed files will appear in the renamedFiles/ subfolder.");
        System.out.println();
        System.out.println("Only 1 CSV file is allowed. Non-PDF/CSV files are ignored.");
        System.out.println("Files that don't match any student will be listed in missingSubmissions.txt.");
        System.out.println();
        System.out.println("Press Enter to start or type EXIT to quit.");
    }
}
