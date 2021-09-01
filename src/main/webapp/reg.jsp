<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!doctype html>
<html lang="en">
<head>
    <title>Регистрация</title>
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
    <form action="<%=request.getContextPath()%>/reg.do" method="post">
        <div class="form-group row">
            <label for="name" class="col-sm-6 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-6 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="email" name="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-6 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="password" name="password" placeholder="Password">
            </div>
        </div>
        <button type="submit" class="btn btn-primary" onclick="return validateRegistration();">Registration</button>
    </form>
</div>
</div>
</body>
</html>