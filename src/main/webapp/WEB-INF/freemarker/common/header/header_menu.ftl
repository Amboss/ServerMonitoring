<#-- =====================================================================
     header menu
        - for common layout
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <ul class="nav">
        <li><a href="../employee/password_update.html">Password change</a></li>
    </ul>
</@security.authorize>

