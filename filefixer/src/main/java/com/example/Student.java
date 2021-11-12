package com.example;

public class Student implements Individual{

    public String PID;
    public String Fname;
    public String Lname;
    public String ID;


    public Student(String PID, String Fname, String Lname, String ID){
        this.PID = PID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.ID = ID;

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
    
}
