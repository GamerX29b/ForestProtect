<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />
<%--@elvariable id="photo" type="List<PagePhotoResult>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="textStandart">
    <a  href="/download.html">Загрузка отчётов</a>
    <a  href="/view.html">Просмотр фото</a>
    <a  href="/ImageVerification.html">Верификация фото</a>
</div>
<body>
    <form>
        <div>Дата от <input name="StartDate">Дата до <input name="EndDate"></div>
        <div>Координаты <input name="Coordinates"></div>
        <div>Наличие нарушений <input type="checkbox" name="violation"></div>
        <button type="button" class="button" onclick="toController()">Найти</button>
    </form>

    <c:forEach items="${photo}" var="exampe">
        ${exampe.id}
        ${exampe.coordinates}
        ${exampe.date}
        ${exampe.document}
        ${exampe.name}
        ${exampe.violation}
    </c:forEach>

</div>
</body>
</html>
