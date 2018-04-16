<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.04.2018
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Приветствую, ${name}
<input type="hidden" name="command" value="GOOGLE_FINISHED">
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="ADD">
    <input type="submit" value="Добавить">
</form>
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="UPDATE">
    <input type="submit" value="Обновить">
</form>
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="DELETE">
    <input type="submit" value="Удалить">
</form>
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="CHECK">
    <input type="submit" value="Просмотреть">
</form>
</body>
</html>
