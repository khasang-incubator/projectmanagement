<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<script>
    var service = 'http://localhost:8080/doc'

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
            }
        });
    };

    var RestPost = function (docNumber, dateDoc, author, type, content) {

        if (!checkData(docNumber, dateDoc, author, type, content)) {
            return;
        }

        var JSONObject = {
            'docNumber': docNumber,
            'docDate': dateDoc,
            'author':author,
            'type':type,
            'content':content
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

    var RestUpdate = function (id, docNumber, dateDoc, author, type, content){

        if (id == ""){
            alert("не указано значение id")
            return;
        }

        if (!checkData(docNumber, dateDoc, author, type, content)) {
            return;
        }

        var JSONObject = {
            'id':id,
            'docNumber': docNumber,
            'docDate': dateDoc,
            'author':author,
            'type':type,
            'content':content
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
    };

    var RestDel = function (id) {

        if (id == ""){
            alert("не указано значение id")
            return;
        }

        $.ajax({
            type: 'DELETE',
            url: service + '/del/' + id,
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

    var checkData= function (docNumber, dateDoc, author, type, content){
        if (docNumber == ""){
            alert("не указано значение docNumber")
            return false;
        }

        if (dateDoc == ""){
            alert("не указано значение dateDoc")
            return false;
        }

        var reg_exp =  /^\d{4}-\d{2}-\d{2}$/;
        if(!reg_exp.test(dateDoc))
        {
            alert("Дату следует ввести в формате yyyy-mm-dd");
            return false;
        }

        if (author == ""){
            alert("не указано значение author")
            return false;
        }

        if (type == ""){
            alert("не указано значение type")
            return false;
        }
        return  true;
    };
</script>

<body>
<h1>Document Menu</h1>

<table class="table">
    <tr>
        <th>Request type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Get all documents - <code><strong>GET</strong></code></td>
        <td>/doc/all</td>
        <td>
            <button type="button" onclick="RestGetAll()">try</button>
        </td>
    </tr>
    <tr>
        <td>Get document by id - <code><strong>GET</strong></code></td>
        <td>/doc/get/{id}</td>
        <td>
            id: <input id="idDoc" value="1"/>
            <button type="button" onclick="RestGet($('#idDoc').val())">try</button>
        </td>
    </tr>
    <tr>
        <td>add new document - <code><strong>POST</strong></code></td>
        <td>/doc/add</td>
        <td>
            <table>
                <tr> <td>docNumber:</td> <td> <input id="docNumber" value="111p"/> </td> </tr>
                <tr> <td>docDate:</td> <td> <input id="docDate" value="2019-06-04"/> </td> </tr>
                <tr> <td>author:</td> <td> <input id="docAuthor" value="noname"/> </td> </tr>
                <tr> <td>type:</td> <td> <input id="docType" value="something type"/> </td> </tr>
                <tr> <td>content:</td> <td> <input id="docContent" value="some of the content"/> </td> </tr>
                <tr> <td></td> <td> <button type="button" onclick="RestPost($('#docNumber').val(), $('#docDate').val(), $('#docAuthor').val(), $('#docType').val(), $('#docContent').val())">try</button> </td> </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>update document - <code><strong>PUT</strong></code></td>
        <td>/document/update{document}</td>
        <td>
            <table>
                <tr> <td>idDocument:</td> <td> <input id="idChangedDoc" value="1"/> </td> </tr>
                <tr> <td>docNumber:</td> <td> <input id="newDocNumber" value="new number"/> </td> </tr>
                <tr> <td>docDate:</td> <td> <input id="newDocDate" value="2019-06-17"/> </td> </tr>
                <tr> <td>author:</td> <td> <input id="newDocAuthor" value="other author"/> </td> </tr>
                <tr> <td>type:</td> <td> <input id="newDocType" value="other type"/> </td> </tr>
                <tr> <td>content:</td> <td> <input id="newDocContent" value="new content"/> </td> </tr>
                <tr> <td></td> <td> <button type="button"
                                                onclick="RestUpdate(
                                                    $('#idChangedDoc').val(),
                                                     $('#newDocNumber').val(),
                                                      $('#newDocDate').val(),
                                                       $('#newDocAuthor').val(),
                                                        $('#newDocType').val(),
                                                         $('#newDocContent').val())">try</button></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>Delete document by id - <code><strong>DELETE</strong></code></td>
        <td>/doc/delete/{id}</td>
        <td>
            id: <input id="idDelDoc" value="1"/>
            <button type="button" onclick="RestDel($('#idDelDoc').val())">try</button>
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