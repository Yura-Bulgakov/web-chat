package org.example.command.show;

import org.example.command.Command;
import org.example.data.MessageDto;
import org.example.result.ForwardResult;
import org.example.result.Result;
import org.example.service.MessageService;
import org.example.service.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

import static org.example.Resources.PAGE_CHAT;

public class ShowChatPageCommand implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        MessageService messageService = MessageServiceImpl.getInstance();
        List<MessageDto> messages = messageService.getAllMessages();
        messages.sort(Comparator.comparing(MessageDto::getTimeStamp));
        session.setAttribute("messages", messages);
        return new ForwardResult(PAGE_CHAT);
    }
}
