package org.example.repository;

import org.example.data.DataBase;
import org.example.data.Message;
import org.example.service.MessageService;
import org.example.service.MessageServiceImpl;
import org.example.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SimpleMessageRepository implements MessageRepository {
    private static volatile MessageRepository instance;
    private final Connection connection;

    public static MessageRepository getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new SimpleMessageRepository();
                }
            }
        }
        return instance;
    }

    private SimpleMessageRepository() {
        this.connection = DataBase.getConnection();
    }

    @Override
    public void add(Message message) {
        String sqlQuery = "INSERT INTO messages (sender_id, content, timestamp) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, message.getSenderId());
            preparedStatement.setString(2, message.getContent());
            preparedStatement.setTimestamp(3, message.getTimestamp());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollBackException) {
                rollBackException.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Message> getAll() {
        List<Message> messageList = new ArrayList<>();
        String sqlQuery = "SELECT messages.id, messages.sender_id, messages.content, messages.timestamp "
                + "FROM messages";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                messageList.add(toMessage(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messageList;
    }

    private Message toMessage(ResultSet resultSet) throws SQLException {
        int messageId = resultSet.getInt("id");
        int senderId = resultSet.getInt("sender_id");
        String content = resultSet.getString("content");
        Timestamp timestamp = resultSet.getTimestamp("timestamp");
        return new Message(messageId, senderId, content, timestamp);
    }
}
