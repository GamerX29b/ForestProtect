<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8"/>
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />

<%--@elvariable id="photolist" type="java.util.List<com.example.ForestProtect.Base.Photos>"--%>

<%-- photolist --%>
<header>

</header>

<body class="background">
<%-- вывод случайных девяти фото  --%>
    <c:forEach items="${photolist}" var="photo">
        <figure class="figure">
            <img src="photo?name=${photo.name}" width="100%">
        </figure>
    </c:forEach>
</body>
<script>


</script>