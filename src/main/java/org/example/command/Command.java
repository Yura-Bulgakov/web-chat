package org.example.command;

import org.example.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Result execute(HttpServletRequest request, HttpServletResponse response);
}
