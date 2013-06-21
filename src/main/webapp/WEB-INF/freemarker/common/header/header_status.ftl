<#-- ==============================================================
     header status for common layout
     ============================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#-- bug with ROLE_ADMIN!!! -->

<@security.authorize ifAnyGranted="ROLE_USER">
    <#assign user="Server Monitoring Service"/>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#assign user="Welcome Admin!"/>
</@security.authorize>
<h1>${user!"Welcome!"}</h1>
