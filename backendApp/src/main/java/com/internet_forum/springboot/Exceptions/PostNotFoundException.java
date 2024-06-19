package com.internet_forum.springboot.Exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException() {super("Post not found");
    }
}
