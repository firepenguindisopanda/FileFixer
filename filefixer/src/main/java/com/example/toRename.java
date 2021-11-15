public class toRename implements Individual {

    public String PID;
    public String name;
    public String ID;
    private java.util.ArrayList <String> toBeRenamed;
    public Boolean AttendanceStatus;

    public toRename (String PID, String name, String ID,Boolean AttendanceStatus){

        this.PID = PID;
        this.name = name;
        this.ID = ID;
        toBeRenamed = new java.util.ArrayList<String>();
        this.AttendanceStatus = AttendanceStatus;


    }

    public String getPID() {
        return PID;
    }

    public String getname() {
        return name;
    }


    public String getID() {
        return ID;
    }

    public java.util.ArrayList<String> getToBeRenamedList(){//Accessor for arraylist that stores the combo list.
        return toBeRenamed;
    }
    
    public void addToList(String originalfilename){//Mutator for the array that stores the combo list.
        toBeRenamed.add(originalfilename);
    }

    public Boolean getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendance(Boolean status) {
        AttendanceStatus = status;
        
    }
    
}
