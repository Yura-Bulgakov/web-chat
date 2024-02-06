package org.example.repository;

import org.example.data.Message;

import java.util.List;

public interface MessageRepository {
    void add(Message message);

    List<Message> getAll();
}
