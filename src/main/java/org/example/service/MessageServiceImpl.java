package org.example.service;

import org.example.data.DataBase;
import org.example.data.Message;
import org.example.data.MessageDto;
import org.example.data.User;
import org.example.repository.MessageRepository;
import org.example.repository.SimpleMessageRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private static volatile MessageService instance;
    private final MessageRepository messageRepository;
    private final UserService userService;

    private MessageServiceImpl() {
        this.messageRepository = SimpleMessageRepository.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    public static MessageService getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new MessageServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(Message message) {
        messageRepository.add(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<MessageDto> messageDtoList = new ArrayList<>();
        List<Message> messageList = messageRepository.getAll();
        for (Message m : messageList) {
            User senderUser = userService.getUserById(m.getSenderId());
            String sender;
            if (senderUser != null) {
                sender = senderUser.getName();
            } else {
                sender = "Ноунейм";
            }
            MessageDto messageDto = new MessageDto(sender, m.getContent(), m.getTimestamp());
            messageDtoList.add(messageDto);
        }
        return messageDtoList;
    }
}
