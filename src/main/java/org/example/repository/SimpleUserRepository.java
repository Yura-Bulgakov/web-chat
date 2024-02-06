package org.example.repository;

import org.example.data.DataBase;
import org.example.data.User;
import org.example.data.UserType;
import org.example.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleUserRepository implements UserRepository {
    private static volatile UserRepository instance;
    private final Connection connection;

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new SimpleUserRepository();
                }
            }
        }
        return instance;
    }

    private SimpleUserRepository() {
        this.connection = DataBase.getConnection();
    }

    @Override
    public Optional<User> getById(int userId) {
        String sqlQuery = "SELECT users.id, users.login, users.password, users.name, user_types.name as userType, "
                + "users.online, users.banned "
                + "FROM users "
                + "JOIN user_types ON users.user_type_id = user_types.id "
                + "WHERE users.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(toUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        String sqlQuery = "SELECT users.id, users.login, users.password, users.name, user_types.name as userType, "
                + "users.online, users.banned "
                + "FROM users "
                + "JOIN user_types ON users.user_type_id = user_types.id "
                + "WHERE users.login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(toUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sqlQuery = "SELECT users.id, users.login, users.password, users.name, user_types.name as userType, "
                + "users.online, users.banned "
                + "FROM users "
                + "JOIN user_types ON users.user_type_id = user_types.id ";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                userList.add(toUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void save(User user) {
        String sqlQuery = "INSERT INTO users (login, password, name, user_type_id) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, getUserTypeId(user.getUserType()));
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
    public void update(User user) {
        String query = "UPDATE users SET password = ?, name = ?, user_type_id = ?, online = ?, banned = ?"
                + " WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, getUserTypeId(user.getUserType()));
            preparedStatement.setBoolean(4, user.isOnline());
            preparedStatement.setBoolean(5, user.isBanned());
            preparedStatement.setString(6, user.getLogin());
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

    private int getUserTypeId(UserType userType) throws SQLException {
        String sqlQuery = "SELECT id FROM user_types WHERE name = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(sqlQuery)) {
            selectStatement.setString(1, userType.toString());
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("User type not found");
            }
        }
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        UserType userType = UserType.valueOf(resultSet.getString("userType"));
        boolean isOnline = resultSet.getBoolean("online");
        boolean isBanned = resultSet.getBoolean("banned");
        return new User(id, login, password, name, userType, isOnline, isBanned);
    }
}
