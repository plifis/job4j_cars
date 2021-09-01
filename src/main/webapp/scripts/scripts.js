/**
 * функция получения всех объявлений в соответствии с фильтром filter
 * @param filter фильтр по которому будет осуществлен отбор объявлений
 */
function getAllAdvt(filter) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/option.do',
        data: {"filter": filter},
        data_type: 'multipart/form-data'
    }).done(function (resp){
        printAdvt(resp);
    }).fail(function (){
        alert("Не удалось получить все объявления");
    });
}


/**
 * функция генерирует html код содержащий список объявлений
 * @param resp ответ сервлета, содержащий список объявлений
 */
function printAdvt(resp) {
    let arrJson = JSON.parse(resp);
    $('tbody').empty();
    for (let i = 0; i < arrJson.length; i++) {
        let idAuthor = arrJson[i].id;
        let email = arrJson[i].user.email;
        let idAdvt;
        let idCar;
        let sale;
        let image;
        let desc;
        let body;
        let mark;
        let created;
        let price;
        let author = arrJson[i].user.name;
        let list = arrJson[i].list;
        for (let y = 0; y < list.length; y++) {
            idCar = list[y].car.id;
            sale = '';
            image = list[y].car.image;
            desc = list[y].car.description;
            body = list[y].car.body;
            mark = list[y].car.mark;
            idAdvt = list[y].id;
            created = list[y].created;
            price = list[y].price;
            let color;
            if (list[y].sale === true) {
                sale ='checked';
                color = 'bgcolor = "lightblue"'
            }
            $('tbody:last').append(
                '<tr onclick="checkAuthor(' + idAuthor +', ' + idCar + ')" '+ color +'>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + desc + '</label></td>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + mark + '</label></td>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + body + '</label></td>' +
                '<td><img src="http://localhost:8080/cars/upload.do?image=' + image +'&name=' + author +
                '" width="200px"> </td>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + price + '</label></td>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + created + '</label></td>' +
                '<td><input class="form-check-input" type="checkbox" name="advt"' + ' ' + sale +' disabled></td>' +
                '<td><label class="form-check-label" for="advt' + i + y + '">' + author + ', email: ' + email + '</label></td>' +
                '<td><input type="hidden" id="idAuthor' + idAuthor + '" value="' + idAuthor +'">' +
                '<input type="hidden" id="idCar' + idCar + '" value="'+ idCar + '"></td>' +
                '</tr>'
            );
        }
    }
}


/**
 * Функция добавления объявления в хранилище,
 * путём создания формы с введенными данными и отправкой данной формы сервлету
 */
function addAdvt() {
    let formData = new FormData();
    formData.append("description", $('#description').val());
    formData.append("mark", $('#mark').val());
    formData.append("body", $('#body').val());
    formData.append("price", $('#price').val());
    formData.append("image", document.getElementById("image").files[0]);
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cars/advt.do',
        data: formData,
        cache: false,
        dataType: 'json',
        processData: false,
        contentType: false,
        scriptCharset: "utf-8"
    }).done(function (){
        window.location.href = '/cars/index.jsp';
    }).fail(function (resp){
        alert("Не удалось добавить объявление" + resp);
    });
}

/**
 * Функция проверки заполнения полей формы при регистрации пользователя
 */
function validateRegistration() {
    let name = $('#name').val();
    let email = $('#email').val();
    let password = $('#password').val();
    if ((name === "") || (email === "") || (password === "")) {
        alert("Все поля должны быть заполнены");
        return false;
    } else {
        return true;
    }
}

/**
 * Функция проверки принадлежноости авторства объяления текущему пользователю
 * @param idAuthor идентификатор автор
 * @param idCar идентификатор автомобиля в объявлении
 */
function checkAuthor(idAuthor, idCar) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/update.do',
        data: {"idAuthor" : idAuthor, "idCar" : idCar},
        data_type: 'text'
    }).done(function (resp) {
        if (resp !== "Объявление может редактировать, только автор.") {
            window.location.href = (resp);
    } else {alert(resp);
        }
    }).fail(function (resp) {
        alert(resp);
    });
}


/**
 * Функция обновления объявления
 */
function updateAdvt() {
    let formData = new FormData();
    formData.append("idAdvt", $('#idAdvt').val());
    formData.append("description", $('#description').val());
    formData.append("mark", $('#mark').val());
    formData.append("body", $('#body').val());
    formData.append("price", $('#price').val());
    formData.append("issale", $('#isSale').val());
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cars/update.do',
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function (resp){
        windows.location.href = "/cars/index.jsp";
    }).fail(function (){
        alert("Не удалось обновить объявление. Просьба обратиться к администратору.");
    })
}

/**
 * Функция удаления объявления
 * @param idAdvt идентификатор объявления
 */
function deleteAdvt(idAdvt) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/deleteAdvt.do',
        date: 'idAdvt=' + idAdvt
    }).done(function (){
        windows.location.href = "/cars/index.jsp";
    }).fail(function (){
    });
}

