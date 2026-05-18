package com.example;

public class Student implements Individual {

    private final String pid;
    private final String name;
    private final String id;
    private boolean attendanceStatus;
    private final String email;
    private final String status;
    private final String grade;
    private final String maxGrade;
    private final boolean gradeCanBeChanged;
    private final String lastModified;

    public Student(String pid, String name, String id, boolean attendanceStatus) {
        this(pid, name, id, "", "", "", "", false, "");
        this.attendanceStatus = attendanceStatus;
    }

    public Student(String pid, String name, String id, String email, String status,
                   String grade, String maxGrade, boolean gradeCanBeChanged, String lastModified) {
        if (pid == null || pid.isBlank()) throw new IllegalArgumentException("PID cannot be null or empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID cannot be null or empty");
        this.pid = pid;
        this.name = name;
        this.id = id;
        this.email = email;
        this.status = status;
        this.grade = grade;
        this.maxGrade = maxGrade;
        this.gradeCanBeChanged = gradeCanBeChanged;
        this.lastModified = lastModified;
    }

    @Override
    public String getPID() {
        return pid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    @Override
    public void setAttendance(boolean status) {
        this.attendanceStatus = status;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getGrade() {
        return grade;
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public boolean isGradeCanBeChanged() {
        return gradeCanBeChanged;
    }

    public String getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "PID: " + pid + " Name: " + name + " ID: " + id + " Attendance Status: " + attendanceStatus;
    }
}
