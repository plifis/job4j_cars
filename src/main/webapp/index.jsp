<%@ page import="ru.job4j.model.Author" %>
<%@ page import="ru.job4j.model.Advt" %>
<%@ page import="ru.job4j.store.HbStore" %>
<%@ page import="ru.job4j.model.User" %>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Объявления</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="scripts/scripts.js"></script>
    <link href="styles/style.css" rel="stylesheet" type="text/css">

</head>
<body onload="getAllAdvt('all')">
<%
    User user = (User) request.getSession().getAttribute("user");
    String name;
    if (user == null) {
       name = "незнакомец";
    } else {
        name = user.getName();
    }
%>
<h1 align="center">
    Автомаркет
</h1>
<div class="row">
<%--    <img class="img-fluid" src="${pageContext.request.contextPath}/img/automarket.jpg">--%>
</div>
<form class="container">
    <div class="row">
        <ul>
            <li><a href="index.jsp">Главная страница</a></li>
            <li><a href="addAdvt.jsp">Добавить объявление</a></li>
            <li><a href="login.jsp">Авторизоваться</a></li>
            <li><a href="reg.jsp">Зарегистрироваться</a></li>
            <li><label>Здравствуйте, <%=name%>
            </label></li>
        </ul>
</div>
</form>

<div class="container" >
<select name="filter" id="filter" onchange="getAllAdvt(this.value)">
    <option value="today">За последний день</option>
    <option value="withPhoto">С фото</option>
    <option selected value="all">Все объявления</option>
</select>
        <input type="text" name="currentName" id="currentName" value="" placeholder="Найти машины определенной марки">
        <button type="submit" class="btn btn-secondary" onclick="getAllAdvt($('#currentName').val())">Показать</button>
    </div>

<div class="container">
    <table class="table" id="Advts">
        <thead class="thead-dark">
        <tr>
            <th>Описание</th>
            <th>Марка</th>
            <th>Кузов</th>
            <th>Фото</th>
            <th>Цена</th>
            <th>Дата публикации</th>
            <th>Продано</th>
            <th>Автор</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
</html>