package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToRename implements Individual {

    private final String pid;
    private final String name;
    private final String id;
    private boolean attendanceStatus;
    private final List<String> toBeRenamed;

    public ToRename(String pid, String name, String id, boolean attendanceStatus) {
        this.pid = pid;
        this.name = name;
        this.id = id;
        this.attendanceStatus = attendanceStatus;
        this.toBeRenamed = new ArrayList<>();
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

    public List<String> getToBeRenamedList() {
        return Collections.unmodifiableList(toBeRenamed);
    }

    public void addToList(String originalFilename) {
        if (originalFilename != null && !originalFilename.isBlank()) {
            toBeRenamed.add(originalFilename);
        }
    }

    public int getFileCount() {
        return toBeRenamed.size();
    }
}
