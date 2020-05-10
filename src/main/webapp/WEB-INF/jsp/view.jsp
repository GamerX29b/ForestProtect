<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8"/>
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />

<%--@elvariable id="photolist" type="java.util.List<com.example.ForestProtect.Base.Photos>"--%>

<%-- photolist --%>
<header>

</header>

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