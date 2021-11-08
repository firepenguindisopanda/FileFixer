
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
  String[][] arr = new String[10][20];
  int i = 0;
 
  
  try {
   reader = new BufferedReader(new FileReader(file));
   while((line = reader.readLine()) != null) {
    
    String[] row = line.split(",");
    
    
    for (int j = 0; j < row.length; j++){
    arr[i][j] = row[j];
    //System.out.println(arr[i][j]);
    }
    i++;
    
    
   
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
  
  System.out.println(arr[5][1]);
  
 }
}
