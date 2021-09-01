<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Добавление объявления</title>
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

    <form id="advtForm">
        <div class="container">
            <div class="form-group">
                <label for="description">Описание автомобиля</label>
                <input type="text" class="form-control" id="description" placeholder="Описание автомобиля">
            </div>
            <div class="form-group">
                <label for="mark">Марка автомобиля</label>
                <input type="text" class="form-control" id="mark" placeholder="Марка автомобиля">
            </div>
            <div class="form-group">
                <label for="body">Тип кузова</label>
                <input type="text" class="form-control" id="body" placeholder="Тип кузова">
            </div>
            <div class="form-group">
                <label for="price">Цена, руб.</label>
                <input type="number" class="form-control" id="price">
            </div>
            <div class="form-group">
    <form id='loadForm' method='POST'>
        <input id='image' type='file' name='image'><br/>
    </form>
            </div>
            <div class="form-group">
                <input type="checkbox" class="form-check" id="isSale">
                <label for="isSale">Продано</label>
            </div>
        </div>
        </form>
    <button type="submit" class="btn btn-primary" onclick="addAdvt()">Разместить объявление</button>
</div>
</div>
</body>
</html>