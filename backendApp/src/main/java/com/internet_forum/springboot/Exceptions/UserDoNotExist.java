package com.internet_forum.springboot.Exceptions;

public class UserDoNotExist extends RuntimeException {

    public UserDoNotExist(Long id) {
        super("Not found user with id: " + id);
    }

    public UserDoNotExist(Integer id) {
        super("Not found user with id: " + id);
    }
}