<#-- ==============================================================
     login form
        - for common header with active form appearance
     ============================================================== -->
<#import "/util/spring.ftl" as spring />
<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />
<#assign form=JspTaglibs["/WEB-INF/tlds/spring-form.tld"] />

<#-- ==============================================================
     Localization
     ============================================================== -->

<ul class="nav">
   <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" >Lang<b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="?language=en">Eng</a></li>
            <li><a href="?language=ru">Ru</a></li>
        </ul>
   </li>
</ul>

<#-- ==============================================================
     logout button
        - appears if user successfully logged in
     ============================================================== -->
<@security.authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
    <form class="navbar-form pull-right" action=<@spring.url
                        value="/j_spring_security_logout" htmlEscape="true"/>  method="POST">
        <button type="submit" class="btn"><i class="icon-off" ></i>&nbsp;Sign out</button>
    </form>
    <p class="navbar-text pull-right" style="padding-right: 20px;">Logged in as ${username!"Anonymous" }</p>
</@security.authorize>

<#-- ==============================================================
     authorisation form
        - disappears if user successfully logged in
     ============================================================== -->
<@security.authorize ifNotGranted="ROLE_ADMIN, ROLE_USER">
    <form  class="navbar-form pull-right"
            id="loginForm"
            method="POST"
            action="<@spring.url value='/j_spring_security_check'/>" >
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




