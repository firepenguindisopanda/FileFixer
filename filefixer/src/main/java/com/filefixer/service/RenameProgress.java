package com.filefixer.service;

import com.filefixer.model.Student;

public interface RenameProgress {

    void onFileProcessed(String fileName, Student student, boolean success, String newFileName);

    void onProgress(int current, int total);

    void onComplete(int renamed, int missing, int review);

    RenameProgress NOOP = new RenameProgress() {
        @Override
        public void onFileProcessed(String fileName, Student student, boolean success, String newFileName) {}
        @Override
        public void onProgress(int current, int total) {}
        @Override
        public void onComplete(int renamed, int missing, int review) {}
    };
}
