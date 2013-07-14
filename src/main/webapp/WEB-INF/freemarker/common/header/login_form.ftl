<#-- ==============================================================
     login form
        - for common header with active form appearance
     ============================================================== -->
<#import "/spring.ftl" as spring />
<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />
<#assign form=JspTaglibs["/WEB-INF/tlds/spring-form.tld"] />

<#-- ==============================================================
     logout button
        - appears if user successfully logged in
     ============================================================== -->
<@security.authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
    <form class="navbar-form pull-right" action=<@spring.url
                        value="/j_spring_security_logout" htmlEscape="true"/>  method="POST">
        <button type="submit" class="btn">Sign out</button>
    </form>
    <p class="navbar-text pull-right" style="padding-right: 20px;">Logged in as ${username!"Anonymous" }</p>
</@security.authorize>

<#-- ==============================================================
     authorisation form
        - disappears if user successfully logged in
     ============================================================== -->
<@security.authorize ifNotGranted="ROLE_ADMIN, ROLE_USER">
    <form action="<@spring.url value='/j_spring_security_check'/>"
                                class="navbar-form pull-right" method="POST" >
        <input class="span2"
                id="j_username"
                name="j_username"
                type="text"
                placeholder="Username"
                autofocus/>
        <input class="span2"
                id="j_password"
                name="j_password"
                type="password"
                placeholder="Password" />
        <input class="btn"
                type="submit"
                value="Sign in"/>
    </form>
</@security.authorize>