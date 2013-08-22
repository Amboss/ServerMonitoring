<#-- =====================================================================
     header menu
        - for common layout
     ===================================================================== -->
<#import "/spring.ftl" as spring />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <ul class="nav">
        <li><a href="<@spring.url '/employee/password_update.html'/>"><@spring.message "header_menu_pass" /></a></li>
    </ul>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <ul class="nav">
        <li><a href="<@spring.url '/settings/change_settings.html'/>"><@spring.message "header_menu_settings" /></a></li>

    </ul>
</@security.authorize>

