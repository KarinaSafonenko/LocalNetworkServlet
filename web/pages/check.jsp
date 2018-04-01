<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<head>
    <title>Локальные сети</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th>Name</th>
        <th>Speed</th>
        <th>Cable</th>
        <th>Standard</th>
    </tr>
    <c:forEach items="${localNetworkSet}" var="localNetwork">
        <tr>
            <td>${localNetwork.name}</td>
            <td>${localNetwork.speed}</td>
            <td>${localNetwork.cable}</td>
            <td>${localNetwork.standard}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
