<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Навигационная панель</title>
    <style>
        <%@include file="../css/styles.css"%>
        <%@include file="../css/navbar.css"%>
    </style>
</head>
<body>
    <div id="nav-bar">
        <c:if test="${user != null}">
            <a href="chat?command=show_chat_page">Чат</a>
        </c:if>
        <c:if test="${user.userType == 'ADMIN'}">
            <a href="admin?command=show_admin_page">Администратор</a>
        </c:if>
        <c:if test="${user != null}">
            <a href="login?command=logout">Выход</a>
            <p>Привет, ${user.name}!</p>
        </c:if>
    </div>
</body>
</html>

