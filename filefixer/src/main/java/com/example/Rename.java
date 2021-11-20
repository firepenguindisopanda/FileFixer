package com.example;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Rename {
    private ToRename filesToRename;
    private static String[] invalid;

    public void startRename(Student[] student, ToRename[] rename, FileCollection fcollection) {
        filesToRename = rename[0];
        int count = 0;
        int lengthForInvalidList = filesToRename.getToBeRenamedList().size() + 1;
        invalid = new String[lengthForInvalidList];
        //Calls three classes for three different types of renaming
            SimpleRename.startRenamingProcess(count, filesToRename.getToBeRenamedList().size(), student, rename, fcollection);
            StandardRename.startRenamingProcess(count, filesToRename.getToBeRenamedList().size(), student, rename, fcollection);
            ComplexRename.startRenamingProcess(count, filesToRename.getToBeRenamedList().size(), student, rename, fcollection);

            count++;

    }
    

    public static int checkValidity(Boolean valid, int numeral,  String[] listOfFilesToBeRenamed, int count){

        if (valid.equals(false) && !listOfFilesToBeRenamed[count].contains(".csv") && listOfFilesToBeRenamed[count].contains(".pdf")){  
            invalid[numeral] = "Problem submission to review: " + listOfFilesToBeRenamed[count];
            numeral++;
        }
        return numeral;
    }

    public static int getLength(Student[] arr){
        int count = 0;
        for(Student el : arr)
            if (el != null)
            count++;
        return count;
    }

    public static void generateTXT(int count, int sizeOfRenamingList, Boolean valid, int numeral, String[] invalidSubmissions, 
    Student[] student, String[] listOfFilesToBeRenamed ){
        if (count == sizeOfRenamingList - 1 && valid == true) {
            System.out.println("\nFiles renamed in renamedFiles folder in filesToRename folder.");
            System.out.println("\nView missingSubmissions.txt in the project folder for the list of missing submissions.\n");
        }
    
    // preparing the missing submissions text file
    System.out.println("Check missingSubmissions.txt in project for missing submissions or submissions that need review.\n");

    int number = 0;
    int num = 0;
    try {
        PrintStream fileOut = new PrintStream(System.getProperty("user.dir") + "/missingSubmissions.txt");
        System.setOut(fileOut);
        int c = 0;
        int counter = 0;
        while (c < getLength(student)){
            Student s1 = student[c];
            while(counter < numeral){
            if(invalid[counter].contains(s1.getname()) || invalid[counter].contains(s1.getID()) ){
                 invalid[counter] = "";
            }
            counter++;
            }
            counter = 0;
            c++;
        }

        while (num < numeral) {
            if(!invalid[num].equals("")){
            System.out.println(invalid[num]);
            }
            num++;
        }

        while (number < getLength(student)) {
            Student missingStudent = student[number];
            if (missingStudent.getAttendanceStatus()) {
                System.out.println("Submission missing: " + missingStudent.getname() + " " + missingStudent.getID());
                

            }
            number++;
        }
        fileOut.close();
    } catch (Exception e) {
       
    }
}


    public static void copyFiles(File originalFile, File newFile) {
        try {
            Files.copy(originalFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occured while copying the files to newpath");
        }
    }
}
