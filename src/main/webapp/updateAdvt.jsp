<%@ page import="ru.job4j.model.Author" %>
<%@ page import="ru.job4j.model.Advt" %>
<%@ page import="ru.job4j.store.HbStore" %>
<%@ page import="ru.job4j.model.User" %>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Редактирование объявления</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="scripts/scripts.js"></script>
    <link href="styles/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<%
    Advt advt = HbStore.instOf().getRowById(Advt.class, Integer.parseInt(request.getParameter("idAdvt")));
    User user = (User) request.getSession().getAttribute("user");
%>
<h1 align="center">
    Автомаркет
</h1>
<div class="row"><img src=""> </div>
<div class="container">
    <div class="row">
        <ul>
            <li><a href="index.jsp">Главная страница</a></li>
            <li><a href="addAdvt.jsp">Добавить объявление</a></li>
            <li><a href="login.jsp">Авторизоваться</a></li>
            <li><a href="reg.jsp">Зарегистрироваться</a></li>
        </ul>

    </div>
<div class="container">

    <div class="card">
        <div class="card-header">
        </div>
        <div class="card-body">
            <input type="number" class="form-control" id="idAdvt" value="<%=advt.getId()%>" hidden>
            <div class="form-group">
                <label for="description">Описание автомобиля</label>
                <input type="text" class="form-control" id="description" value="<%= advt.getCar().getDescription()%>">
            </div>
            <div class="form-group">
                <label for="mark">Марка автомобиля</label>
                <input type="text" class="form-control" id="mark" value="<%= advt.getCar().getMark()%>">
            </div>
            <div class="form-group">
                <label for="body">Тип кузова</label>
                <input type="text" class="form-control" id="body" value="<%= advt.getCar().getBody()%>">
            </div>
            <div class="form-group">
                <label for="price">Цена, руб.</label>
                <input type="number" class="form-control" id="price" value="<%= advt.getPrice()%>">
            </div>
            <div class="form-group">
            <img src="http://localhost:8080/cars/upload.do?image=<%=advt.getCar().getImage()%>&name=<%=user.getName()%>"
                     width="100px" height="100px" alt="Здесь могла быть Ваша картинка." id="file" name="file">
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="isSale" <%if (advt.isSale()) {%>
                checked
                    <%}%>>
                <label for="isSale">Продано</label>
            </div>
            <button type="submit" class="btn btn-primary" onclick="updateAdvt()">Отправить объявление</button>
            <button type="submit" class="btn btn-secondary" onclick="deleteAdvt($('#idAdvt').val())">Удалить объявление</button>
        </div>
    </div>
    </div>
</div>
</body>
</html>