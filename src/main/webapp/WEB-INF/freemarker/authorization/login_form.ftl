<#-- ==============================================================
     login form for common header with active form appearance
     ============================================================== -->
<#import "/spring.ftl" as spring />
<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#-- ==============================================================
     logout button appears if user successfully logged in
     ============================================================== -->
<@security.authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
    <form action=<@spring.url value="/j_spring_security_logout" htmlEscape="true"/> class="navbar-form pull-right" method="POST">
        <button type="submit" class="span2">Sign out</button>
    </form>
</@security.authorize>

<#-- ==============================================================
     login form disappears if user successfully logged in
     ============================================================== -->
<@security.authorize ifNotGranted="ROLE_ADMIN, ROLE_USER">
    <form action="<@spring.url value='/j_spring_security_check'/>" class="navbar-form pull-right"
                                                            method="POST" onSubmit="javascript:disableControls()>
        <#include "../common/message/login_message.ftl">
        <table>
            <tr>
            <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
               <#@spring.message "login.bad.credentials"/>
            </#if>
               <td><label class="span2" for="j_username">Username</label></td>
               <td><input id="j_username" name="j_username" size="20" maxlength="50" type="text"/></td>

            </tr>
            <tr>
               <td><label class="span2" for="j_password">Password</label></td>
               <td><input id="j_password" name="j_password" size="20" maxlength="50" type="password"/></td>

            </tr>
            <tr>
               <td><input  type="submit" class="btn-primary" value="Login"/><input  type="reset" class="btn-primary" value="Cancel"/></td>
            </tr>
        </table>


    </form>
    <a href="password_recovery.html">Password recovery</a>
    <#-- div class="error">${error}</div -->
</@security.authorize>