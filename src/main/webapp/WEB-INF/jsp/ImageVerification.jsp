<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />

<%--@elvariable id="photo" type="com.example.ForestProtect.Base.Photos"--%>
<header>

</header>
<body class="background">
<div id="container" class="textStandart">
    <div id="header">
        <h2>Верификация изображений</h2>
    </div>


    <div>

    <img src="photo?name=${photo.name}" width="100%">

        <form>
            <label value="">Координаты фотографии: ${photo.coordinates}</label>
            <input title="Верифицировать" type="checkbox" name="yes"/>
            <input name="iDphotos" value="${photo.id}"/>


        </form>
    </div>

    <div id="footer">
        <h2>ДНО</h2>
    </div>
</div>






</body>