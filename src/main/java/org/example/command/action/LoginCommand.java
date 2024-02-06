package org.example.command.action;

import org.example.command.Command;
import org.example.data.User;
import org.example.result.ForwardResult;
import org.example.result.RedirectResult;
import org.example.result.Result;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static org.example.Resources.COMMAND_SHOW_CHAT_PAGE;
import static org.example.Resources.COMMAND_SHOW_LOGIN_PAGE;

public class LoginCommand implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");
        UserService userService = UserServiceImpl.getInstance();

        if (userService.authUser(login, password)) {
            userService.updateOnlineStatus(login, true);
            User user = userService.getUserByLogin(login);
            request.getSession().setAttribute("user", user);
            if (user.isBanned()) {
                ServletContext context = request.getServletContext();
                Set<String> bannedUsers = (Set<String>) context.getAttribute("bannedUsers");
                bannedUsers.add(login);
            }
            return new RedirectResult(COMMAND_SHOW_CHAT_PAGE);
        } else {
            request.setAttribute("errorLoginMessage", "Неверные логин или пароль");
            request.setAttribute("loginInput", login);
            return new ForwardResult(COMMAND_SHOW_LOGIN_PAGE);
        }
    }
}
