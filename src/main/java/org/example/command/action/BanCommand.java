package org.example.command.action;

import org.example.command.Command;
import org.example.result.RedirectResult;
import org.example.result.Result;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static org.example.Resources.COMMAND_SHOW_ADMIN_PAGE;

public class BanCommand implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("banLogin");
        UserService userService = UserServiceImpl.getInstance();
        userService.updateBanStatus(login, true);
        ServletContext context = request.getServletContext();
        Set<String> bannedUsers = (Set<String>) context.getAttribute("bannedUsers");
        bannedUsers.add(login);
        return new RedirectResult(COMMAND_SHOW_ADMIN_PAGE);
    }
}
