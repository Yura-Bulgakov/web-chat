package org.example.data;

import java.sql.Timestamp;

public class Message {
    private final int senderId;
    private final String content;
    private final Timestamp timestamp;
    private int id;

    public Message(int id, int senderId, String content, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(int senderId, String content, Timestamp timestamp) {
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
