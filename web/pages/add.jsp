<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.04.2018
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение локальных сетей</title>
</head>
<body>
<form action="/ControllerServlet" method="post">
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Скорость:</td>
            <td><input type="text" name="speed"/></td>
        </tr>
        <tr>
            <td>Кабель:</td>
            <td><input type="text" name="cable"/></td>
        </tr>
        <tr>
            <td>Название стандарта:</td>
            <td><input type="text" name="standard_name"/></td>
        </tr>
        <tr>
            <td>Страна стандарта:</td>
            <td><input type="text" name="standard_land"/></td>
        </tr>
        <input type="submit" value="Добавить">
        <input type="hidden" name="command" value="ADDED">
    </table>
</form>
</body>
</html>
