package com.example;

/**
 * Write a description of class Reader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class Reader {

  String[] pathnames;
  List<String> names = new ArrayList<String>();
  String[][] arr = new String[10][20];

  // String file = "SampleData.csv";

  public void LoadCsvData(String file) throws Exception {

    BufferedReader reader = null;
    String line = "";

    int i = 0;

    try {
      reader = new BufferedReader(new FileReader(file));
      while ((line = reader.readLine()) != null) {

        String[] row = line.split(",");

        for (int j = 0; j < row.length; j++) {
          arr[i][j] = row[j];
          // System.out.println(arr[i][j]);
        }
        i++;

      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {

        e.printStackTrace();
      }
    }

    // System.out.println(arr[5][1]);

  }

  public void LoadDirectoryFiles() throws Exception {

    File f = new File("/Users/nickalwinter/Desktop/untitled folder");
    ////// Replace with path of selected folder on your machine!

    pathnames = f.list();

    for (String pathname : pathnames) {

      String[] rowz = pathname.split("_");

      // Checks number of underscores to determine if to rename or not!
      if (rowz.length > 5) {

        names.add(pathname);

        // Collection of files to rename!
      }

    }

    System.out.println("Files to rename");

    System.out.println(names);

    System.out.println();

  }

}
