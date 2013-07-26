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
                    <li class="nav-header">Employee</li>
                    <li><a href="<@spring.url '/employee_management/employee_manager.html' />"><i class="icon-chevron-right"></i>Employee management</a></li>
                    <li><a href="<@spring.url '/server_management/serv_manager.html' />"><i class="icon-chevron-right"></i>Server manager</a></li>

                    <#-- =====================================================================
                                TODO remove after production
                    =======================================================================-->
                    <#--li><a href="<@spring.url '/employee_management/serv_assignment.html"><i class="icon-chevron-right'/>"></i>Server assignment</a></li-->
                    <#--li><a href="../employee_management/employee_registr.html"><i class="icon-chevron-right"></i>Employee registration</a></li-->
                    <#--li><a href="../employee_management/employee_update.html"><i class="icon-chevron-right"></i>Employee update</a></li-->
                    <#--li><a href="../employee_management/employee_removal.html"><i class="icon-chevron-right"></i>Employee removal</a></li-->
                    <#--li><a href="../employee_management/serv_assignment.html"><i class="icon-chevron-right"></i>Server assignment</a></li-->
                    <li class="nav-header">Server</li>
                    <#--li><a href="<@spring.url '/server_management/serv_registr.html"><i class="icon-chevron-right'/>"></i>Server registration</a></li-->
                    <#--li><a href="<@spring.url '/server_management/serv_update.html"><i class="icon-chevron-right'/>"></i>Server update</a></li-->
                    <#--li><a href="<@spring.url '/server_management/serv_removal.html"><i class="icon-chevron-right'/>"></i>Server removal</a></li-->
                    <#-- =======================================================================

                    =========================================================================-->
                </ul>
            </div>
        </div>
    </div>
</@security.authorize>