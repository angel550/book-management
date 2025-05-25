package com.angeldev.bookmanagement.exception;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException(String className, String field) {
        super(String.format("A %s with the same %s already exists", className, field));
    }
}
