<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<script>
    var service = 'http://localhost:8080/cat'

    var RestGetAll = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
            dataType: 'json',
            accept: 'json',
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };

    var RestGet = function (id) {

        if (id == ""){
            alert("не указано значение id")
            return;
        }

        $.ajax({
            type: 'GET',
            url: service + '/get/' + id,
            dataType: 'json',
            accept: 'json',
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
                return null;
            }
        });
    };

    var RestUpdate = function (id, name, description){

        if (id == ""){
            alert("не указано значение id")
            return;
        }

        var updatedCat = RestGet(id)
        if (updatedCat==null){
            alert("нет объекта с указанным id")
            return;
        }


        var JSONObject = {
            'id':id,
            'name': name,
            'description': description
        };

        $.ajax({
            type: 'PUT',
            url: service + '/update',
            dataType: 'json',
            data: JSON.stringify(JSONObject),
            accept: 'json',
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    }

    var RestPost = function (name, description) {

        if (name == ""){
            alert("не указано значение name")
            return
        }

        var JSONObject = {
            'name': name,
            'description': description
        };

        $.ajax({
            type: 'POST',
            url: service + '/add',
            dataType: 'json',
            data: JSON.stringify(JSONObject),
            accept: 'json',
            contentType: 'application/json;utf-8',
            async: false,
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
<h1>Cat Menu</h1>

<table class="table">
    <tr>
        <th>Request type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Get All cats - <code><strong>GET</strong></code></td>
        <td>/cat/all</td>
        <td>
            <button type="button" onclick="RestGetAll()">try</button>
        </td>
    </tr>
    <tr>
        <td>Get cat by id - <code><strong>GET</strong></code></td>
        <td>/cat/get/{id}</td>
        <td>
            id: <input id="getCatByID" value="1"/>
            <button type="button" onclick="RestGet($('#getCatByID').val())">try</button>
        </td>
    </tr>
    <tr>
        <td>add new cat - <code><strong>POST</strong></code></td>
        <td>/cat/add</td>
        <td>
            name: <input id="catName" value="barsik"/>
            description: <input id="catDescription" value="good"/>
            <button type="button" onclick="RestPost($('#catName').val(), $('#catDescription').val())">try</button>
        </td>
    </tr>
    <tr>
        <td>update cat - <code><strong>PUT</strong></code></td>
        <td>/cat/update{cat}</td>
        <td>
            id: <input id="catID" value="1"/>
            name: <input id="catNameUpdate" value="barsik"/>
            description: <input id="catDescriptionUpdate" value="good"/>
            <button type="button" onclick="RestUpdate($('#catID').val(),$('#catNameUpdate').val(), $('#catDescriptionUpdate').val())">try</button>
        </td>
    </tr>
</table>

<div class="panel panel-default">
    <div class="page-heading">
        <strong>RESPONSE></strong>
    </div>
    <div class="panel-body" id="response"></div>
</div>
</body>
</html>