package com.todotic.bookstoreapi.exeption;

public class BackRequestException extends RuntimeException {

    public BackRequestException(){
        super();
    }
    public BackRequestException(String message) {
        super(message);
    }
}
