package com.example;

import java.io.File;
public class ComplexRename{

    private static String[] listOfFilesToBeRenamed;
    private static int numeral;
    static Boolean valid;

public static void startRenamingProcess(int count, int sizeOfRenamingList, Student[] student, ToRename[] rename, FileCollection f) {

    int nameCounter = 0;
    int counter = 0;
    numeral = 0;
    Boolean entered = false;
    valid = false;
    listOfFilesToBeRenamed = new String[sizeOfRenamingList + 1];
    String[] secondSplitList = new String[6];
    IIterator file = f.createIterator();


    // Add the files to a list to rename
    while(file.hasNext()) {
        listOfFilesToBeRenamed[nameCounter] = file.next().toString();
        nameCounter++;
    }
    count = 0;
    while(count < listOfFilesToBeRenamed.length - 1) {
        secondSplitList = listOfFilesToBeRenamed[count].split(" ");

    while (counter < Rename.getLength(student)) {
        Student aStudentFileToRename = student[counter];
        entered = false;
        valid = false;


        try {
            // if the string at position counter doesn't contain "assignsubmission"
            // and if string at position 0 doesn't contain "-60"
            // and if the attendance value is true
            // enter this if statement
            if (!listOfFilesToBeRenamed[count].contains("assignsubmission") && !secondSplitList[0].contains("-60")
                    && aStudentFileToRename.getAttendanceStatus()) {
                String[] nameSplit = aStudentFileToRename.getname().split(" ");
                if (listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getID())
                        || listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                        || listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname().toUpperCase())
                        || listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname().toLowerCase())
                        || listOfFilesToBeRenamed[count].contains(nameSplit[0])
                                && listOfFilesToBeRenamed[count].contains(nameSplit[1])) {
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
                    if (listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && !listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getID())) {
                        splitOne = listOfFilesToBeRenamed[count].split(aStudentFileToRename.getname());
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
                    if (!listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && !listOfFilesToBeRenamed[count]
                                    .contains(aStudentFileToRename.getname().toUpperCase())
                            && !listOfFilesToBeRenamed[count]
                                    .contains(aStudentFileToRename.getname().toLowerCase())) {
                        splitOne = listOfFilesToBeRenamed[count].split(aStudentFileToRename.getID());
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
                    if (!listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && listOfFilesToBeRenamed[count]
                                    .contains(aStudentFileToRename.getname().toUpperCase())) {
                        splitOne = listOfFilesToBeRenamed[count]
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
                    if (!listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && listOfFilesToBeRenamed[count]
                                    .contains(aStudentFileToRename.getname().toLowerCase())) {
                        splitOne = listOfFilesToBeRenamed[count]
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
                    if (!listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && !listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getID())) {
                        String fileName = aStudentFileToRename.getname();
                        fileName = fileName.replace(" ", "");
                        if (listOfFilesToBeRenamed[counter].contains(fileName)) {
                            nameOfFile = "";
                            splitOne = listOfFilesToBeRenamed[count].split(fileName);
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
                    if (listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getname())
                            && listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getID())) {
                        splitOne = listOfFilesToBeRenamed[count].split(aStudentFileToRename.getname());
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
                        String path = user + "/filesToRename/" + listOfFilesToBeRenamed[count];
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
                        Rename.copyFiles(originalFile, newFile);

                    }

                }

            }

        } catch (Exception e) {
        }
        counter++;
    }
    try{

    } catch (Exception e){

    }
    numeral = Rename.checkValidity(valid, numeral, listOfFilesToBeRenamed, count );
    counter=0;
    count++;
}
   

}

}