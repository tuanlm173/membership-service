package com.tuanle.loyaltyprogram.exceptionhandling;

public class ResourceNotExistException extends RuntimeException {

    private final String message;

    public ResourceNotExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
