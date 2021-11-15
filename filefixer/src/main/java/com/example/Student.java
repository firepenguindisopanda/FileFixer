public class Student implements Individual{

    public String PID;
    public String name;
    public String ID;
    public Boolean AttendanceStatus;


    public Student(String PID, String name, String ID,Boolean AttendaceStatus){
        this.PID = PID;
        this.name = name;
        this.ID = ID;
        this.AttendanceStatus = AttendaceStatus;

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

    public Boolean getAttendanceStatus() {
        return AttendanceStatus;
    }

    
    public void setAttendance(Boolean status) {
        AttendanceStatus = status;
        
    }
    
}
