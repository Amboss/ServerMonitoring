<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Common Page</title>
    </head>
    <body>
        <center>
            <h1>Common Page</h1>
            <h3>Username : ${username}</h3>
            <p>Everyone has access to this page.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ac velit et ante 
                laoreet eleifend. Donec vitae augue nec sem condimentum varius.</p>
            <ul>
                <li><a href="<c:url value="/main/admin" />" >Admin page</a></li>
                <li><a href="<c:url value='/j_spring_security_logout' />" >Logout</a></li>
            </ul>
        </center>
    </body>
</html>