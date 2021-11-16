package com.example;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Rename {

    public Rename() {

    }

    public void startRename(Student[] student, ToRename[] rename) {
        ToRename r1 = rename[0];
        int count = 0;
        while (count < r1.getToBeRenamedList().size()) {

            int counter = 0;
            Student S1 = student[count];
            Boolean entered = false;

            String[] List = new String[r1.getToBeRenamedList().size() + 1];
            String[] splitList = new String[10];
            String[] SecondsplitList = new String[6];
            for (String name : r1.getToBeRenamedList()) {
                List[counter] = name;
                counter++;
            }
            counter = 0;
            while (counter < r1.getToBeRenamedList().size()) {
                entered = false;
                splitList = List[counter].split("_");
                SecondsplitList = List[counter].split(" ");

                try {

                    if (!List[counter].contains("assignsubmission") && !SecondsplitList[0].contains("-60")) {
                        if (S1.getAttendanceStatus().equals(true)) {
                            String[] namesplit = new String[6];
                            namesplit = S1.getname().split(" ");
                            if (List[counter].contains(S1.getID()) || List[counter].contains(S1.getname())
                                    || List[counter].contains(S1.getname().toUpperCase())
                                    || List[counter].contains(S1.getname().toLowerCase())
                                    || List[counter].contains(namesplit[0]) && List[counter].contains(namesplit[1])) {
                                int x = 0;
                                String[] split1 = new String[6];
                                String nameoffile = "";
                                if (List[counter].contains(S1.getname()) && !List[counter].contains(S1.getID())) {
                                    split1 = List[counter].split(S1.getname());
                                    while (x < split1.length) {
                                        if (!split1[x].contains(S1.getID())) {
                                            nameoffile += split1[x];
                                        }
                                        x++;
                                    }
                                    entered = true;
                                }
                                if (!List[counter].contains(S1.getname())
                                        && !List[counter].contains(S1.getname().toUpperCase())
                                        && !List[counter].contains(S1.getname().toLowerCase())) {

                                    split1 = List[counter].split(S1.getID());
                                    while (x < split1.length) {
                                        if (!split1[x].contains(S1.getID()) && !split1[x].contains(S1.getname())) {
                                            nameoffile += split1[x];
                                        }
                                        x++;

                                    }
                                    entered = true;
                                }
                                if (!List[counter].contains(S1.getname())
                                        && List[counter].contains(S1.getname().toUpperCase())) {
                                    split1 = List[counter].split(S1.getname().toUpperCase());
                                    while (x < split1.length) {
                                        if (!split1[x].contains(S1.getID())
                                                && !split1[x].contains(S1.getname().toUpperCase())) {
                                            nameoffile += split1[x];
                                        }
                                        x++;
                                    }
                                    entered = true;
                                }

                                if (!List[counter].contains(S1.getname())
                                        && List[counter].contains(S1.getname().toLowerCase())) {
                                    nameoffile = "";
                                    split1 = List[counter].split(S1.getname().toLowerCase());
                                    while (x < split1.length) {
                                        if (!split1[x].contains(S1.getID())
                                                && !split1[x].contains(S1.getname().toLowerCase())) {
                                            nameoffile += split1[x];
                                        }
                                        x++;
                                    }
                                    entered = true;
                                }

                                if (!List[counter].contains(S1.getname()) && !List[counter].contains(S1.getID())) {
                                    String n = S1.getname();
                                    n = n.replace(" ", "");
                                    if (List[counter].contains(n)) {
                                        nameoffile = "";

                                        split1 = List[counter].split(n);
                                        while (x < split1.length) {
                                            if (!split1[x].contains(S1.getID()) && !split1[x].contains(n)) {
                                                nameoffile += split1[x];
                                            }
                                            x++;
                                        }
                                    }
                                    entered = true;
                                }

                                if (List[counter].contains(S1.getname()) && List[counter].contains(S1.getID())) {
                                    split1 = List[counter].split(S1.getname());
                                    while (x < split1.length) {
                                        if (!split1[x].contains(S1.getname().toUpperCase())) {
                                            nameoffile += split1[x];
                                        }
                                        x++;
                                    }
                                    if (nameoffile.contains(S1.getID())) {
                                        nameoffile = nameoffile.replace(S1.getID(), "");
                                    }
                                    entered = true;
                                }
                                if (entered.equals(true)) {

                                    String user = System.getProperty("user.dir");
                                    String Desktop = user + "/" + "filesToRename" + "/" + "renamedFiles" + "/";
                                    File Desk = new File(Desktop);
                                    Desk.mkdir();
                                    String path = user + "/" + "filesToRename/" + List[counter];
                                    File originalFile = new File(path);
                                    nameoffile = nameoffile.replace(".pdf", "");
                                    nameoffile = nameoffile.replace("()", "");
                                    nameoffile = nameoffile.trim();
                                    if (!nameoffile.contains(".pdf")) {

                                        nameoffile = nameoffile + ".pdf";
                                    }
                                    String pathOut = user + "/" + "filesToRename/renamedFiles/" + S1.getname() + "_"
                                            + S1.getPID() + "_" + "assignsubmission_file" + "_" + nameoffile;
                                    File newFile = new File(pathOut);
                                    S1.setAttendance(false);// False means present
                                    try {
                                        Files.copy(originalFile.toPath(), newFile.toPath(),
                                                StandardCopyOption.REPLACE_EXISTING);
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                        System.out.println("Error");
                                    }
                                }

                            }

                        }
                    }

                    if (splitList[2].equals("assignsubmission")) {
                        if (splitList[0].equals(S1.getname())) {

                            if (splitList.length == 6) {
                                splitList[4] = splitList[4] + "_" + splitList[5];
                            }
                            String user = System.getProperty("user.dir");
                            String Desktop = user + "/" + "filesToRename" + "/" + "renamedFiles";
                            File Desk = new File(Desktop);
                            Desk.mkdir();
                            String path = user + "/" + "filesToRename/" + List[counter];
                            File originalFile = new File(path);
                            String pathOut = user + "/" + "filesToRename/renamedFiles/" + S1.getname() + "_"
                                    + S1.getPID() + "_" + "assignsubmission_file" + "_" + splitList[4];
                            File newFile = new File(pathOut);
                            S1.setAttendance(false);// False means present
                            try {
                                Files.copy(originalFile.toPath(), newFile.toPath(),
                                        StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {

                                e.printStackTrace();
                                System.out.println("Error");
                            }
                        }
                    }

                    if (splitList[0].contains("14") && splitList[0].contains("-") && splitList[0].contains("60")) {
                        if (S1.getname().contains(splitList[1]) && S1.getname().contains(splitList[2])
                                && List[counter].contains(S1.getPID())) { // Checks 2 names and PID to match
                                                                          // patrticipant. PID helps with users with
                                                                          // same name.
                            int num = splitList.length - 2;
                            while (splitList[num].equals(S1.getPID())) {
                                splitList[splitList.length - 1] = splitList[num] + "_"
                                        + splitList[splitList.length - 1];

                                num--;
                            }

                            num = splitList.length - 2;
                            while (!splitList[num].equals(S1.getPID())) {
                                splitList[splitList.length - 2] += "_" + splitList[splitList.length - 1];
                                splitList[splitList.length - 1] = splitList[splitList.length - 2];
                                num--;

                            }

                            String user = System.getProperty("user.dir");
                            String Desktop = user + "/" + "filesToRename" + "/" + "renamedFiles";
                            File Desk = new File(Desktop);
                            Desk.mkdir();
                            String path = user + "/" + "filesToRename/" + List[counter];
                            File originalFile = new File(path);
                            splitList[splitList.length - 1] = splitList[splitList.length - 1].replace(S1.getPID() + "_",
                                    "");
                            String pathOut = user + "/" + "filesToRename/renamedFiles/" + S1.getname() + "_"
                                    + S1.getPID() + "_" + "assignsubmission_file" + "_"
                                    + splitList[splitList.length - 1];
                            File newFile = new File(pathOut);
                            S1.setAttendance(false);// False means present
                            try {
                                Files.copy(originalFile.toPath(), newFile.toPath(),
                                        StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {

                                e.printStackTrace();
                                System.out.println("Error");
                            }

                        }
                    }

                } catch (Exception e) {

                }

                counter++;
            }

            count++;

            if (count == r1.getToBeRenamedList().size() - 1) {
                System.out.println("");
                System.out.println("Files renamed in renamedFiles folder in filesToRename folder.");
                System.out
                        .println("View missingSubmissions.txt in the project folder for list of missing submissions.");
                System.out.println("");
            }

        }

        int number = 0;
        try {
            PrintStream fileOut = new PrintStream(System.getProperty("user.dir") + "/missingSubmissions.txt");
            System.setOut(fileOut);
            while (number < student.length) {
                Student S2 = student[number];
                if (S2.getAttendanceStatus().equals(true)) {
                    System.out.println("Submission missing: " + S2.getname() + " " + S2.getID());

                }
                number++;
            }
        } catch (Exception e) {

        }
    }

}
