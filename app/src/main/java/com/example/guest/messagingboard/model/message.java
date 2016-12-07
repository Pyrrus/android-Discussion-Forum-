package com.example.guest.messagingboard.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 12/6/16.
 */
@Parcel
public class Message {
    private String message;

    private String title;

    private ArrayList<Comment> comments;

    public Message(String message, String title) {
        this.message = message;
        this.title = title;
    }

    public Message() {

    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
