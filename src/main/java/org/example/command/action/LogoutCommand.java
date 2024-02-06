package org.example.command.action;

import org.example.command.Command;
import org.example.data.User;
import org.example.result.ForwardResult;
import org.example.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.example.Resources.PAGE_LOGIN;

public class LogoutCommand implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            request.getSession().invalidate();
        }
        return new ForwardResult(PAGE_LOGIN);
    }
}
