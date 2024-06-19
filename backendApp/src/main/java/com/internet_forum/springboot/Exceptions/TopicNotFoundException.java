package com.internet_forum.springboot.Exceptions;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException() {
        super("Topic not found");
    }

}
