package com.todotic.bookstoreapi.exeption;

public class ResourceNotFounExeption extends RuntimeException{

    public ResourceNotFounExeption(){
        super();
    }

    public ResourceNotFounExeption(String message){
        super(message);
    }
}
