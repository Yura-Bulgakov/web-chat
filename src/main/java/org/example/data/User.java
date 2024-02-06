package org.example.data;

public class User {
    private final int id;
    private final String login;
    private final String password;
    private final String name;
    private final UserType userType;
    private boolean online = false;
    private boolean banned = false;

    public User(int id, String login, String password, String name, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    public User(int id, String login, String password, String name, UserType userType, boolean online, boolean banned) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.online = online;
        this.banned = banned;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
