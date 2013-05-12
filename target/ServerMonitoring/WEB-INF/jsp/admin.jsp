<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <!--link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/ -->
    </head>
    <body>
        <center>
            <div class="admin_block">
                 <h1>Admin Page</h1>

                 <h3>Username : ${username}</h3>

                 <p>Only admins have access to this page.</p>
                 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam
                     ac velit et ante laoreet eleifend. Donec vitae augue nec sem
                     condimentum varius.</p>

                 <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </div>
        </center>
    </body>
</html>