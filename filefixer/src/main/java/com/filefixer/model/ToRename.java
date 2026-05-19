package com.filefixer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToRename {

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

    public String getPID() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

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
