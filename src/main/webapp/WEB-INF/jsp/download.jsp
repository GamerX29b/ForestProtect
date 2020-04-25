<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link type="text/css" href="../css/generalSyleClass.css" rel="stylesheet" />
<%--@elvariable id="subdata" type="String"--%>

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
    ${subdata}
    <c:if test="${not empty subdata} "><label style="color: crimson">${subdata}</label></c:if>


</div>



</body>