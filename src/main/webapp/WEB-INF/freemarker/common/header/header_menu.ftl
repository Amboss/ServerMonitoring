<#-- =====================================================================
     menu for common layout separate appearance for different access roles
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <ul class="nav">
        <li class="divider-vertical"><a href="../employee/password_update.html">Password change</a></li>
        <li class="divider-vertical"><a href="../employee/serv_details.html">Server details</a></li>
        <li class="divider-vertical"><a href="../employee/monitoring.html">Monitoring service</a></li>
    </ul>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <ul class="nav">
        <li class="divider-vertical"><a href="../settings/change_settings.html">Settings</a></li>
        <li class="divider-vertical"><a href="../server_management/serv_registr.html">Server registration</a></li>
        <li class="divider-vertical"><a href="../server_management/serv_update.html">Server update</a></li>
        <li class="divider-vertical"><a href="../server_management/serv_removal.html">Server removal</a></li>
        <li class="divider-vertical"><a href="../server_management/serv_manager.html">Server manager</a></li>
        <li class="divider-vertical"><a href="../employee_management/employee_registr.html">Employee registration</a></li>
        <li class="divider-vertical"><a href="../employee_management/employee_update.html">Employee update</a></li>
        <li class="divider-vertical"><a href="../employee_management/employee_removal.html">Employee removal</a></li>
        <li class="divider-vertical"><a href="../employee_management/employee_manager.html">Employee manager</a></li>
        <li class="divider-vertical"><a href="../employee_management/serv_assignment.html">Server assignment</a></li>
    </ul>
</@security.authorize>
