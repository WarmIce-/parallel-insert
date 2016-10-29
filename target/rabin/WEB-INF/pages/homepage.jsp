<%--
  Created by IntelliJ IDEA.
  User: MyCall
  Date: 10/20/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Home</title>
</head>
<body>
<form id="login" class="form-horizontal" action="${pageContext.request.contextPath}/login" method="get">
<button type="button" class="btn btn-primary" onclick="Signinbutton(this)" >Signin</button>
    <button type="button" class="btn btn-success" onclick="Signupbutton(this)">Signup</button>
    </form>
<script>
    function Signinbutton(thisObj) {
$("#login").submit();
    }
    function Signupbutton(thisObj)
    {
$("#login").attr("action","${pageContext.request.contextPath}/addpage").submit();
    }
    </script>

</body>
</html>
