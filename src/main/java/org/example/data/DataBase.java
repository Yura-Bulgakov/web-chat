package org.example.data;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DataBase {
    private static Connection connection;
    private static Properties properties;

    static {
        properties = loadProperties();
    }

    private DataBase() {
    }

    public static void init() {
        System.out.println("Установка соединения с Базой Данных");
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
            runMigrations();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка при установке соединения с БД", e);
        }
    }

    private static void runMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource(properties.getProperty("db.url"),
                        properties.getProperty("db.username"),
                        properties.getProperty("db.password"))
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }

    public static void close() {
        System.out.println("Закрытие соединения с Базой Данных");
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при закрытии соединения с БД", e);
        }
    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            throw new RuntimeException("Не удалось получиться соединение с БД");
        }
    }

    private static Properties loadProperties() {
        Properties fileProperties = new Properties();
        try (InputStream input = DataBase.class.getClassLoader().getResourceAsStream("/db_config.properties")) {
            if (input != null) {
                fileProperties.load(input);
            } else {
                throw new IOException("Не удалось найти файл настроек БД");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return fileProperties;
    }
}
