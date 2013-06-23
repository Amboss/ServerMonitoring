<#-- ==============================================================
     login form for common header with active form appearance
     ============================================================== -->
<#import "/spring.ftl" as spring />
<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />
<#assign form=JspTaglibs["/WEB-INF/tlds/spring-form.tld"] />

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
        <#include "../message/login_message.ftl">

        <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
           <#@spring.message "login.bad.credentials"/>
        </#if>
        <label class="span2" for="j_username">Username</label>
        <input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
        <label class="span2" for="j_password">Password</label>
        <input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
        <input  type="submit" class="btn-primary" value="Login"/><input  type="reset" class="btn-primary" value="Cancel"/>
    </form>
    <a href="password_recovery.html">Password recovery</a>

</@security.authorize>