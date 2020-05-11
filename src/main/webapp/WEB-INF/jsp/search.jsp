<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />
<%--@elvariable id="photo" type="List<PagePhotoResult>"--%>
<%--@elvariable id="searchPaket" type="SearchPaket>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

<html>
<head>
    <style type="text/css">
        TABLE {
            border: white; /* Цвет фона таблицы */
            border-style: double; /* Рамка вокруг таблицы */
            color: #33CCCC;
            font-size: 16px;
        }
        TD, TH {
            border: 1px solid white; /* Рамка вокруг ячеек */
        }
    </style>
</head>

<body class="background">
<div class="containerNav">
    <ul class="ulNav">
        <li class="one"><a class="aNav" href="/download.html">Загрузка отчётов</a></li>
        <li class="two"><a class="aNav" href="/view.html">Просмотр фото</a></li>
        <li class="three"><a class="aNav" href="/ImageVerification.html">Верификация фото</a></li>
        <li class="four"><a class="aNav" href="/search.html">Поиск</a></li>
        <hr class="hrNav"/><div class="divNavPanel"></div>
    </ul>
</div>

    <div class="textStandart">
        <form action = "/search.html" method = "GET" enctype = "multipart/form-data" id="search">
            <div>ID фотографии <input name="id" value="${searchPaket.id}"></div>
            <div>Имя фотографии <input name="name" value="${searchPaket.name}"></div>
            <div>Дата от <input type="date" name="startDate" value="${searchPaket.startDate}" style="width: 130px">Дата до <input type="date" value="${searchPaket.endDate}" name="endDate" style="width: 130px"></div>
            <div>Координаты <input name="coordinates" value="${searchPaket.coordinates}"></div>
            <div>Наличие нарушений <input type="checkbox" name="violation" value="${searchPaket.violation}"></div>
            <button type="submit" class="button" value="">Найти</button>
        </form>
    </div>
    <div style="text-align: -webkit-center">
        <table  class="textStandart">
            <tr>
                <th>id</th><th>Фотография</th><th>Дата загрузки</th><th>Координаты</th><th>Нарушение</th><th>Текст  документа</th>
            </tr>
        <c:forEach items="${photo}" var="exampe">
            <tr>
                <td>${exampe.id}</td>
                <td>
                    <a class="tooltip" href="#">Фото<span><img class="miniPhoto" src="photo?name=${exampe.name}"/></span></a>
                </td>
                <td>
                    <c:if test="${exampe.date ne null}">
                        <fmt:formatDate pattern = "dd MM yyyy"  value = "${exampe.date}" />
                    </c:if>
                </td>
                <td>${exampe.coordinates}</td>
                <td><c:if test="${exampe.violation}">Да</c:if><c:if test="${!exampe.violation}">Нет</c:if></td>
                <td>${exampe.document}</td>
            </tr>
        </c:forEach>
        </table>
        <div class="textStandart"><c:if test="${searchPaket.page > 0}"><a><-</a></c:if><a>${searchPaket.page + 1}</a><a>-></a></div>
    </div>
</body>
</html>
