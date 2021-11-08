
/**
 * Write a description of class Reader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;

public class Reader {

 public static void main(String[] args) {
  
  
  
  String file = "SampleData.csv";
  BufferedReader reader = null;
  String line = "";
  
  try {
   reader = new BufferedReader(new FileReader(file));
   while((line = reader.readLine()) != null) {
    
    String[] row = line.split(",");
   
     System.out.println(row[1]);
   /**
   for(String index : row) {
     System.out.printf("%-40s", index);
    }
    System.out.println();
    */
   }
  }
  catch(Exception e) {
   e.printStackTrace();
  }
  finally {
   try {
    reader.close();
   } catch (IOException e) {
   
    e.printStackTrace();
   }
  }
 }
}
