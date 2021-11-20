package com.example;

import java.io.File;
import java.util.Scanner;

public class FileFixer {

    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        String user = System.getProperty("user.dir");
        String Desktop = user + "/" + "filesToRename";
        File Desk = new File(Desktop);
        Desk.mkdir();

        System.out.println("Welcome to file fixer!");
        System.out.println(
                "Ensure that the files you wish to rename along with your CSV file is stored in the filesToRename folder located in the project folder.");
        System.out.println("");
        System.out.println("Press Enter to start the program, type HELP for help or type EXIT to exit.");

        String readString = s.nextLine();
        if (readString.equals("HELP") || readString.equals("help")) {
            System.out.println("");
            System.out.println("********HELP********");
            System.out.println("1)Run program.");
            System.out.println(
                    "2)Open the project folder and look for the filesToRename folder that was created when you ran the program.");
            System.out.println(
                    "3)Place your PDF files that you wish to rename and your CSV file in the filesToRename folder.");
            System.out.println("4)Press enter to start the program.");
            System.out.println(
                    "5)Your renamed files can be found in the renamedFiles folder that is located in the filesToRename folder.");
            System.out.println("Files that are not PDF or CSV will be ignored. Only 1 CSV allowed in the folder.");
            System.out.println(
                    "Files that are not valid, meaning they are a PDF but of no relevance ie not an assignment file, will be ignored.");
            System.out.println("");
            System.out.println("Press enter to start the program or type EXIT to exit.");
            readString = s.nextLine();
        }
        while (!readString.equals("HELP") && !readString.equals("Help") && !readString.equals("EXIT")
                && !readString.equals("exit") && !readString.isEmpty()) {
            System.out.println("Invalid input!!!");
            System.out.println("Press enter to start the program, type HELP for help or type EXIT to exit.");
            readString = s.nextLine();
        }

        if (readString.equals("exit") || readString.equals("EXIT")) {
            System.exit(0);
        }
        if (readString.isEmpty()) {
            Reader r1 = new Reader();
            Rename r = new Rename();
            FileCollection f = new FileCollection();
            Student[] student = r1.LoadCsvData();
            ToRename[] rename = r1.LoadDirectoryFiles();
            f.getList(rename[0].getToBeRenamedList());
            r.startRename(student, rename, f);
            s.close();
        }
        s.close();

    }
}