package org.example.filter;

import org.example.data.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static org.example.Resources.COMMAND_SHOW_CHAT_PAGE;


public class BanFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession(false);
        ServletContext context = servletRequest.getServletContext();
        Set<String> bannedUsers = (Set<String>) context.getAttribute("bannedUsers");
        User user = (User) session.getAttribute("user");
        boolean isCommandSendMessage = "send_message".equals(httpServletRequest.getParameter("command"));
        if (isCommandSendMessage && bannedUsers.contains(user.getLogin())) {
            httpServletResponse.sendRedirect(COMMAND_SHOW_CHAT_PAGE);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
