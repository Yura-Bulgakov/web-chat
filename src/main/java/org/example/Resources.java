package org.example;

public final class Resources {

    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_CHAT = "/WEB-INF/jsp/chat.jsp";
    public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin.jsp";

    public static final String COMMAND_SHOW_LOGIN_PAGE = "login?command=show_login_page";
    public static final String COMMAND_SHOW_CHAT_PAGE = "chat?command=show_chat_page";
    public static final String COMMAND_SHOW_ADMIN_PAGE = "admin?command=show_admin_page";

    private Resources() {
    }
}
