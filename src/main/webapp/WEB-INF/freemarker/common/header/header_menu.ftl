<#-- =====================================================================
     header menu
        - for common layout
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <ul class="nav">
        <li><a href="../employee/password_update.html">Password change</a></li>
        <li><a href="../employee/serv_details.html">Server details</a></li>
    </ul>
</@security.authorize>

