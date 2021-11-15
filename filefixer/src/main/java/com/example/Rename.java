import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
public class Rename {
   


public Rename() {
 
}


public void startRename(Student[] student, toRename[] rename) {
    toRename r1 = rename[0];
    int count = 0;
    while(count < r1.getToBeRenamedList().size()){ 
        
        int counter = 0;
        Student S1 = student[count];
        
        String[] List = new String[50];
        String[] splitList = new String[10];
        String[] SecondsplitList = new String[6];
        for (String name: r1.getToBeRenamedList()){ 
            List[counter] = name;
            counter++;
        }
        counter = 0;
        while(counter < r1.getToBeRenamedList().size()){
            splitList = List[counter].split("_");
            SecondsplitList = List[counter].split(" ");
           
       try{

        if(!List[counter].contains("assignsubmission") && !SecondsplitList[0].contains("-60")){
            // if(!List[counter].contains("CSV")){
            // if(!List[counter].contains("assignsubmission")){
               //  else{
                // System.out.println("Error");
                String[] namesplit = new String[6];
                namesplit = S1.getname().split(" ");
                 if(List[counter].contains(S1.getID()) || List[counter].contains(S1.getname()) || List[counter].contains(S1.getname().toUpperCase()) || List[counter].contains(S1.getname().toLowerCase()) || List[counter].contains(namesplit[0]) && List[counter].contains(namesplit[1])){
                     int x = 0;
                     String[] split1 = new String[6];
                     String nameoffile = "";
                     if(List[counter].contains(S1.getname())){
                         split1 = List[counter].split(S1.getname());
                         while (x < split1.length){
                             if(!split1[x].contains(S1.getID())){
                                 nameoffile += split1[x];
                             }
                             x++;
                         }
                     }
                     if(!List[counter].contains(S1.getname())){
                         split1 = List[counter].split(S1.getID());
                         while (x < split1.length){
                             if(!split1[x].contains(S1.getID()) && !split1[x].contains(S1.getname())){
                                 nameoffile += split1[x];
                             }
                             x++;
                         }
                     }
                     if(!List[counter].contains(S1.getname()) && !List[counter].contains(S1.getname()) && List[counter].contains(S1.getname().toUpperCase())){
                         split1 = List[counter].split(S1.getname().toUpperCase());
                         while (x < split1.length){
                             if(!split1[x].contains(S1.getID()) && !split1[x].contains(S1.getname())){
                                 nameoffile += split1[x];
                             }
                             x++;
                         }
                     }
                    
                     x = 0;
                     
                     if(!List[counter].contains(S1.getname()) && !List[counter].contains(S1.getname()) && List[counter].contains(S1.getname().toLowerCase())){
                        nameoffile = "";
                        split1 = List[counter].split(S1.getname().toLowerCase());
                        while (x < split1.length){
                            if(!split1[x].contains(S1.getID()) && !split1[x].contains(S1.getname())){
                                nameoffile += split1[x];
                            }
                            x++;
                        }
                    }

                    String n = S1.getname();
                    n = n.replace(" ", "");
                    if(List[counter].contains(n)){
                        nameoffile = "";

                        split1 = List[counter].split(n);
                        while (x < split1.length){
                            if(!split1[x].contains(S1.getID()) && !split1[x].contains(n)){
                                nameoffile += split1[x];
                            }
                            x++;
                        }
                    }

                     String user = System.getProperty("user.home");
                     String Desktop = user+"\\"+"Desktop"+"\\"+"RenamedFiles";
                     File Desk = new File(Desktop);
                     Desk.mkdir();
                     String path = user+"\\"+"Desktop\\toBeRenamed\\" + List[counter];
                     File originalFile = new File(path);
                     if(!nameoffile.contains(".pdf")){
                         nameoffile = nameoffile + ".pdf";
                     }
                     String pathOut = user+"\\"+"Desktop\\RenamedFiles\\"+S1.getname()+"_"+S1.getPID()+"_"+"assignsubmission_file"+"_"+nameoffile;
                    File newFile = new File(pathOut);
                    S1.setAttendance(false);//False means present
                    try {
                     Files.copy(originalFile.toPath(), newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (Exception e) {
      
     
                     e.printStackTrace();
                     System.out.println("Error");
                 }
                     

                 }


             }

        if(splitList[2].equals("assignsubmission")){
            if(splitList[0].equals(S1.getname())){
               
                if(splitList.length == 6){
                    splitList[4] = splitList[4]+"_"+splitList[5];
                }
                 String user = System.getProperty("user.home");
                 String Desktop = user+"\\"+"Desktop"+"\\"+"RenamedFiles";
                 File Desk = new File(Desktop);
                 Desk.mkdir();
                 String path = user+"\\"+"Desktop\\toBeRenamed\\" + List[counter];
                 File originalFile = new File(path);
                 String pathOut = user+"\\"+"Desktop\\RenamedFiles\\"+S1.getname()+"_"+S1.getPID()+"_"+"assignsubmission_file"+"_"+splitList[4];
                File newFile = new File(pathOut);
                S1.setAttendance(false);//False means present
                try {
                 Files.copy(originalFile.toPath(), newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
  
 
                 e.printStackTrace();
                 System.out.println("Error");
             }
             }
        }
                
                if(splitList[0].contains("14") && splitList[0].contains("-") && splitList[0].contains("60")){
                    if(S1.getname().contains(splitList[1]) && S1.getname().contains(splitList[2]) && List[counter].contains(S1.getPID())){ //Checks 2 names and PID to match patrticipant. PID helps with users with same name.
                        int num = splitList.length - 2;
                        while(splitList[num].equals(S1.getPID())){
                            splitList[splitList.length -1 ] = splitList[num] + "_" + splitList[splitList.length -1 ];
                            num --;
                        }

                        String user = System.getProperty("user.home");
                        String Desktop = user+"\\"+"Desktop"+"\\"+"RenamedFiles";
                        File Desk = new File(Desktop);
                        Desk.mkdir();
                        String path = user+"\\"+"Desktop\\toBeRenamed\\" + List[counter];
                        File originalFile = new File(path);
                        String pathOut = user+"\\"+"Desktop\\RenamedFiles\\"+S1.getname()+"_"+S1.getPID()+"_"+"assignsubmission_file"+"_"+splitList[splitList.length-1];
                       File newFile = new File(pathOut);
                       S1.setAttendance(false);//False means present
                       try {
                        Files.copy(originalFile.toPath(), newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                       }
                       catch (Exception e) {
         
        
                        e.printStackTrace();
                        System.out.println("Error");
                    }

                    }
                }
                
        
        


       }
       catch(Exception e){
     
    }


            counter++;
        }
    
        count++;
   
        }

        System.out.println("Files renamed in folder RenamedFiles on the Desktop");

        int number = 0;
        try{
        while(number < student.length){
            Student S2 = student[number];
            if(S2.getAttendanceStatus().equals(true)){
                System.out.println("Submission missing: "+ S2.getname() + " " + S2.getID());
                
                
            }
            number++;
        }
    }
    catch(Exception e){

    }
}

}
