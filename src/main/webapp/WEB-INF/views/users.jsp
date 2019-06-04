<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<script>
    var service = 'http://localhost:8080/users'

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
            }
        });
    };

    var RestPost = function (name, login, password, functions) {
        var JSONObject = {
            'name': name,
            'login': login,
            'password': password,
            'function': functions
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
<h1>User Menu</h1>

<table class="table">
    <tr>
        <th>Request type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Get All users - <code><strong>GET</strong></code></td>
        <td>/users/all</td>
        <td>
            <button type="button" onclick="RestGetAll()">try</button>
        </td>
    </tr>
    <tr>
        <td>Get user by id - <code><strong>GET</strong></code></td>
        <td>/users/get/{id}</td>
        <td>
            id: <input id="getUserByID" value="1"/>
            <button type="button" onclick="RestGet($('#getUserByID').val())">try</button>
        </td>
    </tr>
    <tr>
        <td>add new user - <code><strong>POST</strong></code></td>
        <td>/users/add</td>
        <>
            name: <input id="userName" value="defaultName"/>
            login: <input id="userLogin" value="defaultLogin"/>
            password: <input id="userPassword" value="defaultPassword"/>
            function: <input id="userfunction" value="defaultFunction"/>
            <button type="button" onclick="RestPost($('#userName').val(), $('#userLogin').val(), $('#userPassword').val(), $('#userfunction').val())">try</button>
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