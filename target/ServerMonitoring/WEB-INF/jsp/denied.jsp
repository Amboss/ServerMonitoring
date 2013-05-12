<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access denied</title>
        <!--link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/ -->
    </head>
    <body>
        <center>
            <div class="denied_message">
                <h1>Access Denied!</h1>
                <h3>Username : ${username}</h3>
                <p>You do not have rights to see this page!</p>
            </div>
        </center>
    </body>
</html>