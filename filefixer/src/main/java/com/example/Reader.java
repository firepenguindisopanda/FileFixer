package com.example;

import java.io.*;
import java.util.*;

public class Reader {
  private Student Students[];
  private ToRename toRename[];
  String[] pathnames;


  public Reader() {

    Students = new Student[200];
    toRename = new ToRename[1];
  }

  public Student[] LoadCsvData() {
    try {

      String user = System.getProperty("user.dir");
      File f = new File(user + "/filesToRename");
      pathnames = f.list();
      int numcsv = 0;
      String csvpath = "";

      for (String pathname : pathnames) {

        if (pathname.contains(".csv")) {
          numcsv++;
          csvpath = user + "/filesToRename/" + pathname;
        }

      }

      if (numcsv > 1) {// Error handling for multiple CSVs
        System.out.println("");
        System.out.println("Mulitple CSV files were found in the filesToRename folder!!!");
        System.out.println("Please place only 1 CSV file in the folder and rerun the program.");
        System.out.println("");
        System.exit(0);
      }

      if (numcsv == 0) {// Error handling for no CSVs
        System.out.println("");
        System.out.println("No CSV files were found in the filesToRename folder!!!");
        System.out.println("Please place only 1 CSV file in the folder and rerun the program.");
        System.out.println("");
        System.exit(0);

      }

      String path = csvpath;
      File dataFile = new File(path);
      Scanner fileReader = new Scanner(dataFile);
      String[] details = new String[11];
      String[] pid = new String[2];
      int count = 0;
      String line;
      line = fileReader.nextLine();// skips CSV headings.
      while (fileReader.hasNextLine()) {

        line = fileReader.nextLine();
        details = line.split(",");
        pid = details[0].split("\\s+");// Split at whitespace and tab space for PID (Takes out the word "Participant")

        Students[count] = new Student(pid[1], details[1], details[2], true);

        count++;

      }

      fileReader.close();
    } catch (Exception e) {

    }
    return Students;
  }

  public ToRename[] LoadDirectoryFiles() throws Exception {
    String user = System.getProperty("user.dir");
    try {
      File f = new File(user + "/filesToRename");

      pathnames = f.list();

      if (pathnames.length == 0) {
        System.out.println("");
        System.out.println("No files found in toBeRenamed folder!!!");
        System.out.println("Re-run program and type HELP for help.");
        System.out.println("");
        System.exit(0);
      }

      toRename[0] = new ToRename("default", "default", "default", true);
      for (String pathname : pathnames) {

        toRename[0].addToList(pathname);

      }
    } catch (Exception e) {

    }

    return toRename;
  }

}
