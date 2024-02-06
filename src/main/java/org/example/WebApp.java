package org.example;

import org.example.command.Command;
import org.example.command.CommandType;
import org.example.command.EmptyCommand;
import org.example.data.DataBase;
import org.example.result.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebApp extends HttpServlet {

    @Override
    public void init() {
        DataBase.init();
        System.out.println("Произошла инициализации бд");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Command command = defineCommand(request);
        Result result = command.execute(request, response);

        String resourceName = result.getResource();
        switch (result.getResponseType()) {
            case FORWARD:
                request.getRequestDispatcher(resourceName).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(resourceName);
                break;
            default:
                break;
        }
    }

    private Command defineCommand(HttpServletRequest request) {
        String commandString = request.getParameter("command");
        if (commandString == null || commandString.isEmpty()) {
            return new EmptyCommand();
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.EMPTY;
        }
        return commandType.getCommand();
    }

    @Override
    public void destroy() {
        DataBase.close();
    }
}
