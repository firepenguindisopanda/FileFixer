public class toRename implements Individual {

    public String PID;
    public String Fname;
    public String Lname;
    public String ID;
    private java.util.ArrayList <String> toBeRenamed;

    public toRename (String PID, String Fname, String Lname, String ID){

        this.PID = PID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.ID = ID;
        toBeRenamed = new java.util.ArrayList<String>();


    }

    public String getPID() {
        return PID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getID() {
        return ID;
    }

    public java.util.ArrayList<String> getToBeRenamedList(){//Accessor for arraylist that stores the combo list.
        return toBeRenamed;
    }
    
    public void addToCombo(String originalfilename){//Mutator for the array that stores the combo list.
        toBeRenamed.add(originalfilename);
    }
    
}
