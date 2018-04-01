<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.04.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление локальной сети</title>
</head>
<body>
<form action="/ControllerServlet" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <input type="submit" value="Удалить">
        <input type="hidden" name="command" value="DELETED">
    </table>
</form>
</body>
</html>
