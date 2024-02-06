<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <link rel="icon" href="data:,">
    <head>
        <title>Чат</title>
        <style>
            <%@include file="../css/styles.css"%>
            <%@include file="../css/chat.css"%>
        </style>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <div align="center" id="chat-container">
            <!-- Блок для вывода сообщений -->
            <div>
                <h3>Сообщения:</h3>
                <c:forEach var="message" items="${messages}">
                    <div class="message-container">
                        <div class="message-sender">
                            <strong>${message.sender}</strong>
                        </div>
                        <div class="message-content">
                            ${message.content}
                        </div>
                        <div class="message-time">
                            ${message.time}
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
                <c:if test="${!bannedUsers.contains(user.login)}">
                    <form action="chat?command=send_message" method="post">
                        <label for="message">Введите сообщение:</label>
                        <input type="text" id="message" name="content" required>
                        <button type="submit">Отправить</button>
                    </form>
                </c:if>
            </div>
        </div>
    </body>
</html>