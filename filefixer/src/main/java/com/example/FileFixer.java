
public class FileFixer
{
    
    
    public static void main(String[] args) throws Exception{
    
    
    Reader r1 = new Reader();
    Rename r = new Rename();
    String user = System.getProperty("user.home");
    Student[] student = r1.LoadCsvData(user+"/Desktop/toBeRenamed/Sample.csv");
    toRename[] rename = r1.LoadDirectoryFiles();
    r.startRename(student, rename);

}
}