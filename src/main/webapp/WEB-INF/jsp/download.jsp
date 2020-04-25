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
    <form action = "/upload" method = "post" enctype = "multipart/form-data" id="upload">
        <input type = "file" id = "file" size = "50" />
        <br/>
        <input type = "submit" value = "Upload File" />
        <br/>
        <input type="date" id="date"/>
        <br/>
        Широта <input id="xcoord" name="xcoord">
        <br/>
        Долгота <input id="ycoord" name="ycoord">
    </form>
    ${subdata}
    <c:if test="${not empty subdata} "><label style="color: crimson">${subdata}</label></c:if>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

    $(function() {
        $('#go_on').click(function(){

            var data = jQuery('#upload').find('input,select,textarea').serialize();

//--console.debug(data);

            var fd = new FormData();
            fd.append('id', '123');
            fd.append('type', 'one');
            fd.append('img', $('#imgFile')[0].files[0]);

            $.ajax({
                type: 'POST',
                url: '/upload',
                data: fd,
                processData: false,
                contentType: false,
                dataType: "text",
                success: function(data) {
                    $(".message_title").html('OK');
                    $(".message").html(data);
                },
                error: function(data) {
                    $(".message_title").html('Error');
                    $(".message").html(data);
                }
            });
        });
    });
</script>