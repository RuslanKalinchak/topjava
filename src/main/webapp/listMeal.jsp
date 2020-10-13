<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Meal Id</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td><c:out value="${meal.mealId}" /></td>
            <td><c:out value="${meal.dateTime}" /></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
            <td><c:out value="${meal.excess}" /></td>
            <td><a href="MealController?action=edit&mealId=<c:out value="${meal.mealId}"/>">Update</a></td>
            <td><a href="MealController?action=delete&mealId=<c:out value="${meal.mealId}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="MealController?action=insert">Add Meal</a></p>
</body>
</html>