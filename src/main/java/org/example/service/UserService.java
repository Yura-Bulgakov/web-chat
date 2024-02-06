package org.example.service;

import org.example.data.User;

import java.util.List;

public interface UserService {
    boolean authUser(String login, String password);

    List<User> getListUsers();

    User getUserById(int id);

    User getUserByLogin(String login);

    void updateOnlineStatus(String login, boolean online);

    void updateBanStatus(String login, boolean banned);
}
