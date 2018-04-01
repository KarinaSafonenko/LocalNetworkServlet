<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.04.2018
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Локальные сети</title>
</head>
<body>
<form>
    Локальные сети предстали перед Вами!
</form>
<form action="/ControllerServlet" method="get">
    <input type="hidden" name="command" value="CHANGE">
    <input type="submit" value="Изменить">
</form>
<form action="/ControllerServlet" method="get">
    <input type="hidden" name="command" value="CHECK">
    <input type="submit" value="Просмотреть">
</form>
</body>
</html>
