package org.example.listener;

import org.example.data.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User sessionUser = (User) event.getSession().getAttribute("user");
        UserService userService = UserServiceImpl.getInstance();

        if (sessionUser != null) {
            userService.updateOnlineStatus(sessionUser.getLogin(), false);
            System.out.println("Были очищены данные сессии для клиента - " + sessionUser.getName());
        }
    }
}
