<#-- ==============================================================
     login form
        - lang changing switch
        - for common header with active form appearance
     ============================================================== -->
<#import "/util/spring.ftl" as spring />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#assign form=JspTaglibs["/WEB-INF/tlds/spring-form.tld"] />

<#-- ==============================================================
     Localization
     ============================================================== -->

<ul class="nav">
   <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" ><@spring.message "header.lang_switch" /><b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="?locale=en"><@spring.message "header.lang_switch_eng" /></a></li>
            <li><a href="?locale=ru"><@spring.message "header.lang_switch_ru" /></a></li>
        </ul>
   </li>
</ul>

<#-- ==============================================================
     logout button
        - appears if user successfully logged in
     ============================================================== -->

<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />

<@security.authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
    <form method="POST" class="navbar-form pull-right"
          action=<@spring.url value="/j_spring_security_logout" htmlEscape="true"/>  >

        <#import "/util/spring.ftl" as spring />
        <button type="submit" class="btn">
            <i class="icon-off" ></i>&nbsp;<@spring.message "header.signout" /></button>
    </form>
    <p class="navbar-text pull-right" style="padding-right: 20px;">
        <@spring.message 'header.status.as' /> ${username!"<@spring.message 'header.status.anonim' />" }</p>
</@security.authorize>

<#-- ==============================================================
     authorisation form
        - disappears if user successfully logged in
        - TODO  why <@spring.message '' /> not working ???
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




