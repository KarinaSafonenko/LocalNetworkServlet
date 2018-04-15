<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="GOOGLE">
    <input type="submit" value="Зайти через google">
</form>
<form action="<c:url value="/ControllerServlet"/>" method="get">
    <input type="hidden" name="command" value="VK">
    <input type="submit" value="Зайти через vk">
</form>
</body>
</html>
