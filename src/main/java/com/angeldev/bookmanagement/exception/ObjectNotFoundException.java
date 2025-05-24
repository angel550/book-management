package com.angeldev.bookmanagement.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String className, String objectId) {
        super(String.format("Object of type: %s not found: %s", className, objectId));
    }
}
