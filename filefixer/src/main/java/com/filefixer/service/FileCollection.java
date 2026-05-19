package com.filefixer.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileCollection implements Iterable<String> {

    private final List<String> filesToRename = new ArrayList<>();

    public void setFiles(List<String> files) {
        filesToRename.clear();
        if (files != null) {
            filesToRename.addAll(files);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return filesToRename.iterator();
    }

    public int size() {
        return filesToRename.size();
    }
}
