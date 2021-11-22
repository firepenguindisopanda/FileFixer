package com.example;
/**
 * Instances can be found in Rename, Reader, Student and ToRename classes
 */
public interface Individual{
    public String getPID();
    public String getname();
    public String getID();
    public Boolean getAttendanceStatus();
    public void setAttendance(Boolean status);
}