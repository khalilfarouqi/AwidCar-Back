package com.khalil.wdcar.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1765406791640341247L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
