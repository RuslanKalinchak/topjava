<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>
    <td>
        <c:forEach items="${meals}" var="meal">
            <p>${meal.dateTime}
            ${meal.description}
            ${meal.calories}
            ${meal.excess}</p><br/>
        </c:forEach>
    </td>
</h2>
</body>
</html>
