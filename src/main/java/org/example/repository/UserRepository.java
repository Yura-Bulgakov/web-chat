package org.example.repository;

import org.example.data.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(int id);

    Optional<User> getByLogin(String login);

    List<User> getAll();

    void save(User user);

    void update(User user);
}
