package com.filefixer.exception;

public class FileFixerException extends Exception {

    public FileFixerException(String message) {
        super(message);
    }

    public FileFixerException(String message, Throwable cause) {
        super(message, cause);
    }
}
