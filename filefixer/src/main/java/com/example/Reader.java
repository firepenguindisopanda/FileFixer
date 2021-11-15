import java.io.*;
import java.util.*;

public class Reader {
  private Student Students[];
  private toRename toRename[];
  String[] pathnames;
  List <String> names = new ArrayList<String>();
  String[][] arr = new String[10][20];

public Reader () {

  Students = new Student[100];
  toRename = new toRename[1];
}  
 
  
 
 

public Student[] LoadCsvData(String filename){
    try{
       String path = filename;
        File dataFile = new File(path);
        Scanner fileReader = new Scanner(dataFile);
        String[] details = new String[11];
        String[] pid = new String[2];
        int count = 0;
        String line;
        line = fileReader.nextLine();//skips CSV headings.
        while(fileReader.hasNextLine()) {

           line = fileReader.nextLine();
           details = line.split(",");
           pid = details[0].split("\\s+");//Split at whitespace and tab space for PID (Takes out the word "Participant")
   
        Students[count] = new Student(pid[1], details[1], details[2], true);
 

        count++;
            
        }

        fileReader.close();     
    }
    catch(Exception e){
      
    }
    return Students;
}
 
public toRename[] LoadDirectoryFiles() throws Exception{
        String user = System.getProperty("user.home");
        File f = new File(user+"/Desktop/toBeRenamed");
        //SAVE SAMPLE1 FOLDER FROM MYELEARNING ON DESKTOP
        
        
        pathnames = f.list();
        toRename[0] = new toRename("default", "default", "default", true);
        for (String pathname : pathnames) {
            
 
            
            toRename[0].addToList(pathname);
       

        }
       
    
        
        return toRename;
}




}
