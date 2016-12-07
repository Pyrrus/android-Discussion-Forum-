package com.example.guest.messagingboard.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 12/6/16.
 */
@Parcel
public class Category {
    private String name;
    private ArrayList<Message> Message = new ArrayList<Message>();
    private String pushId;

    public Category(String name) {
        this.name = name;
    }

    public Category(ArrayList<Message> message, String name) {
        Message = message;
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public ArrayList<Message> getMessage() {
        return Message;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }



}
