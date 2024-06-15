package com.internet_forum.springboot.Exceptions;

public class UserDoNotExist extends RuntimeException{
    public UserDoNotExist(String message) {
        super(message);
    }

}
