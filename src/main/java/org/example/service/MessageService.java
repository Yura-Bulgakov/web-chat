package org.example.service;

import org.example.data.Message;
import org.example.data.MessageDto;

import java.util.List;

public interface MessageService {
    void add(Message message);

    List<MessageDto> getAllMessages();
}
