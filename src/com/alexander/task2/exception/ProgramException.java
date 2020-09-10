package com.alexander.task2.exception;

public class ProgramException extends Exception {
    public ProgramException() {
    }

    public ProgramException(String message) {
        super(message);
    }

    public ProgramException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramException(Throwable cause) {
        super(cause);
    }
}
