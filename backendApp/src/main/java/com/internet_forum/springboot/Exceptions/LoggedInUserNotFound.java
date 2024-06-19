package com.internet_forum.springboot.Exceptions;

public class LoggedInUserNotFound extends RuntimeException {
    public LoggedInUserNotFound() { super("Logged in user not found");
    }
}
