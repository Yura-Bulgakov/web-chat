package org.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Set<String> bannedUsers = new HashSet<>();
        context.setAttribute("bannedUsers", bannedUsers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
