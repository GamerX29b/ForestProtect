<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />
<%--@elvariable id="Info" type="java.lang.String"--%>
<%--@elvariable id="photo" type="com.example.ForestProtect.Base.Photos"--%>
    <head>
        <script ENGINE="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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

<div id="container" class="textStandart">
    <div id="header">
        <h2>Верификация изображений</h2>
    </div>
    <c:if test="${photo ne null}">
    <div>
    <img src="photo?name=${photo.name}" style="max-height: 400px; border-radius: 10px;">

        <form name="general" action="/ImageVerification.html">
            <input name="iDphotos" value="${photo.id}" hidden="hidden"/>
            <label value="">Координаты фотографии: ${photo.coordinates}</label>
            <br/>
                <div >
                    <input title="Это значит что картинка действительно является фотографией лесного массива" id="yes" type="checkbox" name="yes" onclick="hidenOrUnhidden()">Верифицировать</input>
                </div>
                <div id="violationDiv" style="display: none" onclick="hidenOrUnhiddenTwo()">
                    <input title="Это значит что на фото есть признаки нарушения" id="violation" type="checkbox" name="violation" >Нарушение</input>
                </div>
                <div id="contentDiv" style="display: none">
                    <textarea  name="content" id="content" type="text" style="width: 500px; height: 200px" placeholder="Введите увиденное вами нарушение"></textarea>
                </div>
                <div>
                    <button type="button" class="button" onclick="toController()">Верифицировать</button>
                </div>
        </form>
    </div>
    </c:if>
    <div class="textStandart">
        ${Info}
    </div>
</div>
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
<script type="text/javascript" charset="utf-8">
    function hidenOrUnhidden() {
        if($('#yes').prop("checked") === true){
            $('#violationDiv').show();
        } else {
            $('#violationDiv').hide();
        }
    }
    function hidenOrUnhiddenTwo() {
        if($('#violation').prop("checked") === true){
            $('#contentDiv').show();
            } else {
            $('#contentDiv').hide();
        }
    }
     function toController() {
         var form = document.forms.general;

        if($('#violation').prop("checked")) {
            if($('#content').val() !== ""){
                form.submit();
            } else {
                alert("Введите обнаруженное вами нарушение");
            }
        } else {
            form.submit();
        }
    }
</script>