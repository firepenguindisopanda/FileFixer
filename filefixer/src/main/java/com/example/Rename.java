package com.example;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Rename {
    private ToRename filesToRename;
    private String[] listOfFilesToBeRenamed;
    private String[] firstSplitList;
    private String[] invalidSubmissions;
    private int numeral;
    private Boolean valid;

    public void startRename(Student[] student, ToRename[] rename) {
        filesToRename = rename[0];
        int count = 0;
        int lengthForInvalidList = filesToRename.getToBeRenamedList().size() + 1;
        invalidSubmissions = new String[lengthForInvalidList];
        // Loop through each submission in the list and
        // start the renaming process
        while (count < filesToRename.getToBeRenamedList().size()) {
            startRenamingProcess(count, filesToRename.getToBeRenamedList().size(), student, rename);

            count++;
            if (count == filesToRename.getToBeRenamedList().size() - 1 && valid == true) {
                System.out.println("\nFiles renamed in renamedFiles folder in filesToRename folder.");
                System.out.println(
                        "\nView missingSubmissions.txt in the project folder for the list of missing submissions.\n");

            }
        }
        // preparing the missing submissions text file
        System.out.println(
                "Check missingSubmissions.txt in project for missing submissions or submissions that need review.\n");

        int number = 0;
        int num = 0;
        try {
            PrintStream fileOut = new PrintStream(System.getProperty("user.dir") + "/missingSubmissions.txt");
            System.setOut(fileOut);
            while (num < numeral) {
                System.out.println(invalidSubmissions[num]);
                num++;
            }
            // Here is where the null error is happening
            // it says cannot invoke Student.getAttendanceStatus() because missing student
            // us null
            while (number < student.length) {
                Student missingStudent = student[number];
                if (!missingStudent.getAttendanceStatus()) {
                    System.out
                            .println("Submission missing: " + missingStudent.getname() + " " + missingStudent.getID());

                }
                number++;
            }
            fileOut.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Error preparing the missing submissions text file");
        }

    }

    public void startRenamingProcess(int count, int sizeOfRenamingList, Student[] student, ToRename[] rename) {
        int nameCounter = 0;
        int counter = 0;
        numeral = 0;
        Student aStudentFileToRename = student[count];
        Boolean entered = false;
        valid = false;
        listOfFilesToBeRenamed = new String[sizeOfRenamingList + 1];
        firstSplitList = new String[10];
        String[] secondSplitList = new String[6];

        // Add the files to a list to rename
        for (String nameOfFile : filesToRename.getToBeRenamedList()) {
            listOfFilesToBeRenamed[nameCounter] = nameOfFile;
            nameCounter++;
        }
        while (counter < filesToRename.getToBeRenamedList().size()) {
            entered = false;
            valid = false;
            firstSplitList = listOfFilesToBeRenamed[counter].split("_");
            secondSplitList = listOfFilesToBeRenamed[counter].split(" ");

            try {
                // if the string at position counter doesn't contain "assignsubmission"
                // and if string at position 0 doesn't contain "-60"
                // and if the attendance value is true
                // enter this if statement
                if (!listOfFilesToBeRenamed[counter].contains("assignsubmission") && !secondSplitList[0].contains("-60")
                        && aStudentFileToRename.getAttendanceStatus()) {
                    String[] nameSplit = aStudentFileToRename.getname().split(" ");
                    if (listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getID())
                            || listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                            || listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname().toUpperCase())
                            || listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname().toLowerCase())
                            || listOfFilesToBeRenamed[counter].contains(nameSplit[0])
                                    && listOfFilesToBeRenamed[counter].contains(nameSplit[1])) {
                        valid = true;
                        int splitCount = 0;
                        String[] splitOne = new String[6];
                        String nameOfFile = "";
                        /**
                         * if at position counter it contains the value of
                         * aStudentFileToRename.getname() and it doesn't contain an ID corresponding to
                         * aStudentFileToRename.getId() value create the name of the file and assign it
                         * to nameOfFile
                         */
                        if (listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && !listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getID())) {
                            splitOne = listOfFilesToBeRenamed[counter].split(aStudentFileToRename.getname());
                            while (splitCount < splitOne.length) {
                                if (!splitOne[splitCount].contains(aStudentFileToRename.getID())) {
                                    nameOfFile += splitOne[splitCount];
                                }
                                splitCount++;
                            }
                            entered = true;
                        }

                        /**
                         * checks if String at position counter doesn't contain
                         * aStudentFileToRename.getname() value and different variations of it.
                         * 
                         */
                        if (!listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && !listOfFilesToBeRenamed[counter]
                                        .contains(aStudentFileToRename.getname().toUpperCase())
                                && !listOfFilesToBeRenamed[counter]
                                        .contains(aStudentFileToRename.getname().toLowerCase())) {
                            splitOne = listOfFilesToBeRenamed[counter].split(aStudentFileToRename.getID());
                            while (splitCount < splitOne.length) {
                                if (!splitOne[splitCount].contains(aStudentFileToRename.getID())
                                        && !splitOne[splitCount].contains(aStudentFileToRename.getname())) {
                                    nameOfFile += splitOne[splitCount];
                                }
                                splitCount++;
                            }
                            entered = true;

                        }

                        /**
                         * checks if String at position counter doesn't contain
                         * aStudentFileToRename.getname() value and if it does contain the uppercase
                         * version of the name
                         * 
                         */
                        if (!listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && listOfFilesToBeRenamed[counter]
                                        .contains(aStudentFileToRename.getname().toUpperCase())) {
                            splitOne = listOfFilesToBeRenamed[counter]
                                    .split(aStudentFileToRename.getname().toUpperCase());
                            while (splitCount < splitOne.length) {
                                if (!splitOne[splitCount].contains(aStudentFileToRename.getID())
                                        && !splitOne[splitCount]
                                                .contains(aStudentFileToRename.getname().toUpperCase())) {
                                    nameOfFile += splitOne[splitCount];
                                }
                                splitCount++;
                            }
                            entered = true;
                        }

                        /**
                         * checks if String at position counter doesn't contain
                         * aStudentFileToRename.getname() value and if it does contain the lowercase
                         * version of the name
                         */
                        if (!listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && listOfFilesToBeRenamed[counter]
                                        .contains(aStudentFileToRename.getname().toLowerCase())) {
                            splitOne = listOfFilesToBeRenamed[counter]
                                    .split(aStudentFileToRename.getname().toLowerCase());
                            while (splitCount < splitOne.length) {
                                if (!splitOne[splitCount].contains(aStudentFileToRename.getID())
                                        && !splitOne[splitCount]
                                                .contains(aStudentFileToRename.getname().toLowerCase())) {
                                    nameOfFile += splitOne[splitCount];
                                }
                                splitCount++;
                            }
                            entered = true;
                        }

                        /**
                         * checks if String at position counter doesn't contain the name value
                         * corresponding to the current aStudentFileToRename Object and if String at
                         * position counter doesn't contain the ID value corresponding to the current
                         * aStudentFileToRename Object
                         */
                        if (!listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && !listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getID())) {
                            String fileName = aStudentFileToRename.getname();
                            fileName = fileName.replace(" ", "");
                            if (listOfFilesToBeRenamed[counter].contains(fileName)) {
                                nameOfFile = "";
                                splitOne = listOfFilesToBeRenamed[counter].split(fileName);
                                while (splitCount < splitOne.length) {
                                    if (!splitOne[splitCount].contains(aStudentFileToRename.getID())
                                            && !splitOne[splitCount].contains(fileName)) {
                                        nameOfFile += splitOne[splitCount];
                                    }
                                    splitCount++;
                                }
                            }
                            entered = true;
                        }

                        /**
                         * checks if the String at position counter contains the aStudentFileToRename
                         * getname() value and getID() value
                         * 
                         */
                        if (listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getname())
                                && listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getID())) {
                            splitOne = listOfFilesToBeRenamed[counter].split(aStudentFileToRename.getname());
                            while (splitCount < splitOne.length) {
                                if (!splitOne[splitCount].contains(aStudentFileToRename.getname().toUpperCase())) {
                                    nameOfFile += splitOne[splitCount];
                                }
                                splitCount++;
                            }
                            if (nameOfFile.contains(aStudentFileToRename.getID())) {
                                nameOfFile = nameOfFile.replace(aStudentFileToRename.getID(), "");

                            }
                            entered = true;

                        }

                        if (entered) {
                            String user = System.getProperty("user.dir");
                            String desktop = user + "/" + "filesToRename" + "/" + "renamedFiles/";
                            File desk = new File(desktop);
                            desk.mkdir();
                            String path = user + "/filesToRename/" + listOfFilesToBeRenamed[counter];
                            File originalFile = new File(path);
                            nameOfFile = nameOfFile.replace(".pdf", "");
                            nameOfFile = nameOfFile.replace("()", "");
                            nameOfFile = nameOfFile.trim();
                            if (!nameOfFile.contains(".pdf")) {
                                nameOfFile = nameOfFile + ".pdf";
                            }
                            String pathToRenamedFiles = user + "/filesToRename/renamedFiles/"
                                    + aStudentFileToRename.getname() + "_" + aStudentFileToRename.getPID()
                                    + "_assignsubmission_file_" + nameOfFile;
                            File newFile = new File(pathToRenamedFiles);
                            aStudentFileToRename.setAttendance(false); // this means present
                            copyFiles(originalFile, newFile);

                        }

                    }

                }

                if (firstSplitList[2].equals("assignsubmission")) {
                    valid = true;
                    if (firstSplitList[0].equals(aStudentFileToRename.getname())) {
                        if (firstSplitList.length == 6) {
                            firstSplitList[4] = firstSplitList[4] + "_" + firstSplitList[5];
                        }
                        String user = System.getProperty("user.dir");
                        String desktop = user + "/filesToRename/renamedFiles";
                        File desk = new File(desktop);
                        desk.mkdir();
                        String path = user + "/filesToRename/" + listOfFilesToBeRenamed[counter];
                        File originalFile = new File(path);
                        String pathToRenamedFiles = user + "/filesToRename/renamedFiles/"
                                + aStudentFileToRename.getname() + "_" + aStudentFileToRename.getPID()
                                + "_assignsubmission_file_" + firstSplitList[4];
                        File newFile = new File(pathToRenamedFiles);
                        aStudentFileToRename.setAttendance(false); // False means present
                        copyFiles(originalFile, newFile);
                    }
                }

                if (firstSplitList[0].contains("14") && firstSplitList[0].contains("-")
                        && firstSplitList[0].contains("60")) {
                    valid = true;
                    if (aStudentFileToRename.getname().contains(firstSplitList[1])
                            && aStudentFileToRename.getname().contains(firstSplitList[2])
                            && listOfFilesToBeRenamed[counter].contains(aStudentFileToRename.getPID())) {
                        int num = firstSplitList.length - 2;
                        while (firstSplitList[num].equals(aStudentFileToRename.getPID())) {
                            firstSplitList[firstSplitList.length - 1] = firstSplitList[num] + "_"
                                    + firstSplitList[firstSplitList.length - 1];
                            num--;
                        }
                        num = firstSplitList.length - 2;
                        while (!firstSplitList[num].equals(aStudentFileToRename.getPID())) {
                            firstSplitList[firstSplitList.length - 2] += "_"
                                    + firstSplitList[firstSplitList.length - 1];
                            firstSplitList[firstSplitList.length - 1] = firstSplitList[firstSplitList.length - 2];

                            num--;
                        }
                        String user = System.getProperty("user.dir");
                        String desktop = user + "/filesToRename/renamedFiles";
                        File desk = new File(desktop);
                        desk.mkdir();
                        String path = user + "/filesToRename/" + listOfFilesToBeRenamed[counter];
                        File originalFile = new File(path);
                        firstSplitList[firstSplitList.length - 1] = firstSplitList[firstSplitList.length - 1]
                                .replace(aStudentFileToRename.getPID() + "_", "");
                        String pathToRenamedFiles = user + "/filesToRename/renamedFiles/"
                                + aStudentFileToRename.getname() + "_" + aStudentFileToRename.getPID()
                                + "_assignsubmission_file_" + firstSplitList[firstSplitList.length - 1];
                        File newFile = new File(pathToRenamedFiles);
                        aStudentFileToRename.setAttendance(false); // False means present
                        copyFiles(originalFile, newFile);

                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
            counter++;
        }
        try {
            if (valid == false && !listOfFilesToBeRenamed[count].contains(".csv")) {
                invalidSubmissions[numeral] = "Problem submission to review: " + listOfFilesToBeRenamed[count];
                numeral++;

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Error adding submissions to invalid pile!");

        }

    }

    public void copyFiles(File originalFile, File newFile) {
        try {
            Files.copy(originalFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occured while copying the files to newpath");
        }
    }
}
