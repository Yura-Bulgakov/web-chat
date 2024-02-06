package org.example.command.action;

import org.example.command.Command;
import org.example.data.Message;
import org.example.data.User;
import org.example.result.RedirectResult;
import org.example.result.Result;
import org.example.service.MessageService;
import org.example.service.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import static org.example.Resources.COMMAND_SHOW_CHAT_PAGE;

public class SendMessageCommand implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        MessageService messageService = MessageServiceImpl.getInstance();
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user");
        if (!user.isBanned()) {
            messageService.add(new Message(user.getId(), content, new Timestamp(System.currentTimeMillis())));
        }
        return new RedirectResult(COMMAND_SHOW_CHAT_PAGE);
    }
}
