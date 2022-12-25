package com.mozcan.readingIsGood.exception;

public class CustomerExistException extends RuntimeException{

    public CustomerExistException(String message) {
        super(message);
    }
}
