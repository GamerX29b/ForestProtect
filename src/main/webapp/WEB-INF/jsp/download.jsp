<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />
<%--@elvariable id="subdata" type="String"--%>

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
<div class="textStandart">
    Загрузка файлов отчётов
    <form action = "/upload" method = "post" enctype = "multipart/form-data" id="upload">
        <input type = "file" id = "file" size = "50" name="file" accept="image/jpeg,image/png,image/gif/,image/"/>
        <br/>
        Дата снимка <input type="date" id="date" name="date"/>
        <br/>
        Широта <input id="xcoord" name="xcoord">
        <br/>
        Долгота <input id="ycoord" name="ycoord">
        <br/>
        <input type = "submit" value = "Загрузить фото"  />
    </form>
    ${subdata}
    <c:if test="${not empty subdata} "><label style="color: crimson">${subdata}</label></c:if>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

</script>