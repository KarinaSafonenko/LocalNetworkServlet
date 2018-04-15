<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновить локальную сеть</title>
</head>
<body>
<form action="/ControllerServlet" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" name="id"/></td>
        </tr>
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
        <input type="submit" value="Обновить">
        <input type="hidden" name="command" value="UPDATED">
    </table>
</form>
</body>
</html>
