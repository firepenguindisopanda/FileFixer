package com.example;

import java.io.File;

public class SimpleRename {

    private static String[] listOfFilesToBeRenamed;
    private static String[] firstSplitList;
    private static String[] invalidSubmissions;
    private static int numeral;
    static Boolean valid;

    public static void startRenamingProcess(int count, int sizeOfRenamingList, Student[] student, ToRename[] rename,
            FileCollection f) {
        int nameCounter = 0;
        int counter = 0;
        numeral = 0;
        valid = false;
        listOfFilesToBeRenamed = new String[sizeOfRenamingList + 1];
        invalidSubmissions = new String[100];
        firstSplitList = new String[10];
        IIterator file = f.createIterator();

        while (file.hasNext()) {
            listOfFilesToBeRenamed[nameCounter] = file.next().toString();
            nameCounter++;
        }
        count = 0;
        while (count < listOfFilesToBeRenamed.length - 1) {
            firstSplitList = listOfFilesToBeRenamed[count].split("_");

            try {
                while (counter < Rename.getLength(student)) {
                    Student aStudentFileToRename = student[counter];
                    try {
                        valid = false;
                        // if the string at position counter doesn't contain "assignsubmission"
                        // and if string at position 0 doesn't contain "-60"
                        // and if the attendance value is true
                        // enter this if statement
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
                                String path = user + "/filesToRename/" + listOfFilesToBeRenamed[count];
                                File originalFile = new File(path);
                                String pathToRenamedFiles = user + "/filesToRename/renamedFiles/"
                                        + aStudentFileToRename.getname() + "_" + aStudentFileToRename.getPID()
                                        + "_assignsubmission_file_" + firstSplitList[4];
                                File newFile = new File(pathToRenamedFiles);
                                aStudentFileToRename.setAttendance(false); // False means present
                                Rename.copyFiles(originalFile, newFile);
                            }

                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    counter++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            numeral = Rename.checkValidity(valid, numeral, listOfFilesToBeRenamed, count);

            counter = 0;
            count++;
        }
        Rename.generateTXT(count, sizeOfRenamingList, valid, numeral, invalidSubmissions, student,
                listOfFilesToBeRenamed);

    }
    public String[] getListOfFilesToBeRenamed(){
        return SimpleRename.listOfFilesToBeRenamed;
    }
    
    public String[] getFirstSplitList(){
        return SimpleRename.firstSplitList;
    }
    public String[] getInvalidSubmissions(){
        return SimpleRename.invalidSubmissions;
    }
    public int getNumeral(){
        return SimpleRename.numeral;
    }

}
