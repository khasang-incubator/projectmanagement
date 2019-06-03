<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascriptja"></script>
    <link rel="stylesheet" href="maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<script>
    var service = 'http://localhost:8080/cat/'
    
    var RestGetAll = function () {
        $.ajax({
            type:'GET',
            url:service + '/all',
            datatype:'json',
            accept:'json',
            contentType:'application/json;utf-8',
            async:false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };
</script>
<body>
<p>It's very secure page for admins'</p>
</body>
</html>