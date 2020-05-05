<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8"/>
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />

<%--@elvariable id="photolist" type="java.util.List<com.example.ForestProtect.Base.Photos>"--%>

<%-- photolist --%>
<header>

</header>

<body class="background">
<div class="textStandart">
    <a  href="/download.html">Загрузка отчётов</a>
    <a  href="/view.html">Просмотр фото</a>
    <a  href="/ImageVerification.html">Верификация фото</a>
</div>
<div class="bigCenterText">Четыре верифицированных фото</div>
<div class="textStandart">Здесь представлены четыре случайных верифицированных фото. На каждое можно навести курсор и узнать его координаты.</div>
<%-- вывод случайных девяти фото  --%>
    <c:forEach items="${photolist}" var="photo">
        <figure class="figure">
            <img title="${photo.coordinates}" src="photo?name=${photo.name}" width="100%" class="photo">
        </figure>
    </c:forEach>
</body>
<script>


</script>