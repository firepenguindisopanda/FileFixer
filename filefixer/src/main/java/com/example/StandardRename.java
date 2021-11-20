package com.example;

import java.io.File;



public class StandardRename {
    private static String[] listOfFilesToBeRenamed;
    private static String[] firstSplitList;
    private static String[] invalidSubmissions;
    private static int numeral;
    static Boolean valid;
    
    public static void startRenamingProcess(int count, int sizeOfRenamingList, Student[] student, ToRename[] rename, FileCollection f) {
        int nameCounter = 0;
        int counter = 0;
        numeral = 0;
        valid = false;
        listOfFilesToBeRenamed = new String[sizeOfRenamingList + 1];
        invalidSubmissions = new String [100];
        firstSplitList = new String[10];
        IIterator file = f.createIterator();


        // Add the files to a list to rename
        while(file.hasNext()) {
            listOfFilesToBeRenamed[nameCounter] = file.next().toString();
            nameCounter++;
        }
        count = 0;
        while(count < listOfFilesToBeRenamed.length - 1) {
            firstSplitList = listOfFilesToBeRenamed[count].split("_"); 

        while (counter < Rename.getLength(student) ) {
            Student aStudentFileToRename = student[counter];
            try {
                valid = false;
                if (firstSplitList[0].contains("14") && firstSplitList[0].contains("-") && firstSplitList[0].contains("60")) {
                    valid = true;
                    if (aStudentFileToRename.getname().contains(firstSplitList[1])
                            && aStudentFileToRename.getname().contains(firstSplitList[2])
                            && listOfFilesToBeRenamed[count].contains(aStudentFileToRename.getPID())) {
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
                        String path = user + "/filesToRename/" + listOfFilesToBeRenamed[count];
                        File originalFile = new File(path);
                        firstSplitList[firstSplitList.length - 1] = firstSplitList[firstSplitList.length - 1]
                                .replace(aStudentFileToRename.getPID() + "_", "");
                        String pathToRenamedFiles = user + "/filesToRename/renamedFiles/"
                                + aStudentFileToRename.getname() + "_" + aStudentFileToRename.getPID()
                                + "_assignsubmission_file_" + firstSplitList[firstSplitList.length - 1];
                        File newFile = new File(pathToRenamedFiles);
                        aStudentFileToRename.setAttendance(false); // False means present
                        Rename.copyFiles(originalFile, newFile);

                    }
                }

            } catch (Exception e) {
                
            }
            counter++;
        }

        try{
        if (!valid && !listOfFilesToBeRenamed[count].contains(".csv") && listOfFilesToBeRenamed[count].contains(".pdf") && SimpleRename.valid.equals(false) && ComplexRename.valid.equals(false)){  
            invalidSubmissions[numeral] = "Problem submission to review: " + listOfFilesToBeRenamed[count];
            numeral++;
        }
        } catch (Exception e){
            
        }

        numeral = Rename.checkValidity(valid, numeral, listOfFilesToBeRenamed, count );
        counter=0;
        count++;
    }
        Rename.generateTXT(count, sizeOfRenamingList, valid, numeral, invalidSubmissions, student, listOfFilesToBeRenamed);

    }


}
