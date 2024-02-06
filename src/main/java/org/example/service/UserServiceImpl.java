package org.example.service;

import org.example.data.DataBase;
import org.example.data.User;
import org.example.repository.SimpleMessageRepository;
import org.example.repository.SimpleUserRepository;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static volatile UserService instance;
    private final UserRepository userRepository;

    private UserServiceImpl() {
        this.userRepository = SimpleUserRepository.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean authUser(String login, String enteredPassword) {
        Optional<User> user = userRepository.getByLogin(login);
        return user.isPresent() && user.get().getPassword().equals(enteredPassword);
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.getById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.getByLogin(login);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.getAll();
    }

    @Override
    public synchronized void updateOnlineStatus(String login, boolean online) {
        Optional<User> user = userRepository.getByLogin(login);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setOnline(online);
            userRepository.update(updatedUser);
        }
    }

    @Override
    public synchronized void updateBanStatus(String login, boolean banned) {
        Optional<User> user = userRepository.getByLogin(login);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setBanned(banned);
            userRepository.update(updatedUser);
        }

    }
}
