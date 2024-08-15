package com.udit.soln.exceptions;

public class InvalidInputCsvFileException extends RuntimeException {
    public InvalidInputCsvFileException(String msg) {
        super((msg));
    }
}
