<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <link rel="icon" href="data:,">
    <head>
        <title>Администратор</title>
        <style>
            <%@include file="../css/styles.css"%>
            <%@include file="../css/admin.css"%>
        </style>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <div align="center" id="admin-container">
            <h2>Список пользователей</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Login</th>
                        <th>Name</th>
                        <th>Online Status</th>
                        <th>Banned Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.name}</td>
                            <td>${user.online}</td>
                            <td>${user.banned}</td>
                            <td>
                                <a href="admin?command=ban&banLogin=${user.login}">Забанить</a>
                                <a href="admin?command=unban&banLogin=${user.login}">Разбанить</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>