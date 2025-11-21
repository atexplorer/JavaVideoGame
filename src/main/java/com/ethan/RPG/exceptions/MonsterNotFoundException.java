package com.ethan.RPG.exceptions;


public class MonsterNotFoundException extends RuntimeException{
    private String message;

    public MonsterNotFoundException() {}

    public MonsterNotFoundException(String msg){
        super(msg);
        this.message = msg;
    }
}
