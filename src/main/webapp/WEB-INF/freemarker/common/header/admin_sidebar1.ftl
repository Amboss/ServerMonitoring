<#-- =====================================================================
     admin sidebar menu
        - for admin layout
        - appearance for ROLE_ADMIN access
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <div class="well sidebar-nav">
        <ul class="nav nav-list">
            <li class="nav-header">Server</li>
            <li><a href="../server_management/serv_registr.html">Server registration</a></li>
            <li><a href="../server_management/serv_update.html">Server update</a></li>
            <li><a href="../server_management/serv_removal.html">Server removal</a></li>
            <li><a href="../server_management/serv_manager.html">Server manager</a></li>
            <li class="nav-header">Employee</li>
            <li><a href="../employee_management/employee_registr.html">Employee registration</a></li>
            <li><a href="../employee_management/employee_update.html">Employee update</a></li>
            <li><a href="../employee_management/employee_removal.html">Employee removal</a></li>
            <li><a href="../employee_management/employee_manager.html">Employee manager</a></li>
            <li><a href="../employee_management/serv_assignment.html">Server assignment</a></li>
        </ul>
    </div>
</@security.authorize>