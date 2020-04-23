package com.tuanle.loyaltyprogram.exceptionhandling;

public class InvalidParameterException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public InvalidParameterException(String message) {
        this.message = message;
    }
}
