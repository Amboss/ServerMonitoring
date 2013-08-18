<#-- =====================================================================
     admin sidebar menu
        - for admin layout
        - appearance for ROLE_ADMIN access
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <div class="container">
        <div class="row">
            <div class="span3 bs-docs-sidebar">
                <ul class="nav nav-list bs-docs-sidenav affix-top">
                    <li class="nav-header">Functions</li>
                    <li><a href="<@spring.url '/employee_management/employee_manager.html' />"><i class="icon-chevron-right"></i>Employee management</a></li>
                    <li><a href="<@spring.url '/server_management/serv_manager.html' />"><i class="icon-chevron-right"></i>Server manager</a></li>
                </ul>
            </div>
        </div>
    </div>
</@security.authorize>