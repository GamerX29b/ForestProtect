<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />

<header>

</header>
<body class="background">

<div class="textStandart">
    Загрузка файлов отчётов
    <form action = "/upload" method = "post"
          enctype = "multipart/form-data">
        <input type = "file" name = "file" size = "50" />
        <br/>
        <input type = "submit" value = "Upload File" />
        <br/>
        <input type="date" name="date"/>
        <br/>
        X координаты <input id="xcoord" name="xcoord">
        <br/>
        Y координаты <input type="ycoord" name="ycoord">
    </form>


    <label style="color: crimson">вблизи указанных вами координат уже имеется изображение </label><a href="http:\\yandex.ru">посмотреть</a>

</div>



</body>