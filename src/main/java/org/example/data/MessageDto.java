package org.example.data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDto {
    private final String sender;
    private final String content;
    private final Timestamp timeStamp;
    private final String time;

    public MessageDto(String sender, String content, Timestamp timeStamp) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = timeStamp;
        LocalDateTime localDateTime = timeStamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.time = localDateTime.format(formatter);
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public String getTime() {
        return time;
    }
}
