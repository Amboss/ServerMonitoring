<#-- ==============================================================
     header status
        - for common layout
     ============================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <#assign user="Server Monitoring Service"/>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#assign user="Welcome Admin!"/>
</@security.authorize>
<h1>${user!"Welcome!"}</h1>
