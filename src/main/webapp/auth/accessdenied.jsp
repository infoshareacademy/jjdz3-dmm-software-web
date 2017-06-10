<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Accessdenied</title>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    </head>
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">DMM-Software Finanncial-App</a>
    </div>
</nav>
<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <h2 class="form-signin-heading">Security: Access Denied!</h2>
        <a class="btn btn-danger" type="button" href="http://localhost:8080/financial-app/login">Go to login page</a>
    </div>

</div>
<footer class="footer">
    <div class="container">
        <p class="navbar-text"> DMM. Financal Application</p>
    </div>
</footer>
</body>
</html>
