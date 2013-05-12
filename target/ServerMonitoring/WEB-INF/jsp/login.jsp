<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    <link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/>
    </head>
    <body onload='document.f.j_username.focus();'>
        <center>
            <div class="login_block">
                <h1>Please Login</h1>
                <c:if test="${not empty error}">
                    <div class="error_block">
                        Your login attempt was not successful, try again.<br />
                        Caused: ${error}
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                         You have been logged out.
                    </div>
                </c:if>
                <form class="login_form_block" action="../j_spring_security_check" method="post" >
                    <p>
                        <label for="j_username">Username</label>
                        <input id="j_username" name="j_username" size="20" maxlength="50" type="text" />
                    </p>

                    <p>
                        <label for="j_password">Password</label>
                        <input id="j_password" name="j_password" size="20" maxlength="50" type="password" />
                    </p>

                    <input  type="submit" value="Login"/>
                    <input  type="reset" value="Cancel"/>
                </form>
            </div>
        </center>
    </body>
</html>