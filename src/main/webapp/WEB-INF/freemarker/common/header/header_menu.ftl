<#-- =====================================================================
     header menu
        - for common layout
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <ul class="nav">
        <li><a href="<@spring.url '/employee/password_update.html'/>">Password change</a></li>
    </ul>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <ul class="nav">
        <#--li><a href="<@spring.url '/employee_management/employee_manager.html'/>"></i>Employee manager</a></li-->
        <#--li><a href="<@spring.url '/server_management/serv_manager.html'/>"></i>Server manager</a></li-->
        <li><a href="<@spring.url '/settings/change_settings.html'/>"></i>Settings</a></li>
    </ul>
</@security.authorize>

