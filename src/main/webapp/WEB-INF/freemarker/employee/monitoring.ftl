<#-- =====================================================================
     Server Monitoring page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#assign pageTitle><@spring.message "monitoring.title" /></#assign>

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>

<@com.page title="${pageTitle}">

    <#-- ===================== JQuery functions ========================= -->
    <#assign reloadTime = tableReloadTime />
    <#include "/util/js/reloadTable.ftl"/>

    <#include "/util/js/sortMonitoringTable.ftl"/>
    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for Server Monitoring table ========================= -->
    <div class="row-fluid">
        <hr>
        <table cellpadding="0"
                cellspacing="0"
                border="0"
                id="monitoringTable"
                class="table table-striped table-bordered">
            <thead> <#-- ===================== thead ========================= -->
                <tr>
                    <th><@spring.message "monitoring.table.server_name" /></th>
                    <th><@spring.message "monitoring.table.state" /></th>
                    <th><@spring.message "monitoring.table.last_check" /></th>
                    <th><@spring.message "monitoring.table.active" /></th>
                </tr>
            </thead>

            <tbody> <#-- ===================== tbody ========================= -->
                <#if availableServers?has_content>

                    <#list availableServers as server >

                        <#-- ===================== Server state icons ========================= -->
                        <#if server.state = "OK">
                            <#assign stateIcon><i class="icon-ok" ></#assign>
                        <#elseif server.state = "WARN">
                            <#assign stateIcon><i class="icon-warning-sign" ></#assign>
                        <#elseif server.state = "FAIL">
                            <#assign stateIcon><i class="icon-ban-circle" ></#assign>
                        </#if>

                        <#-- ===================== Server active icons ========================= -->
                        <#if server.active = 1>
                            <#assign activeIcon><i class="icon-thumbs-up" ></#assign>
                        <#elseif server.active = 0>
                            <#assign activeIcon><i class="icon-ban-circle" ></#assign>
                        </#if>

                        <#assign serverName = server.server_name />
                        <tr>
                            <td><a href="<@spring.url '/employee/serv_details/${serverName}.html' />">
                                <i class="icon-search" ></i>&nbsp;${serverName}<a/></td>
                            <td>${server.state}</td>
                            <td>${server.lastCheck}</td>
                            <td>${activeIcon}</td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td>- - -</td>
                        <td>- - -</td>
                        <td>- - -</td>
                        <td>- - -</td>
                    </tr>
                </#if>
        </tbody>
        </table>
        </br><hr>
    </div>

    <#-- ===================== Information row for Registration page ========================= -->
    <#import "/util/spring.ftl" as spring />
    <div class="row-fluid">
        <div class="span6">
            <h2><@spring.message "monitoring.inf.list_serv" /></h2>
            <p><@spring.message "monitoring.inf.list_serv.text" /></p>
        </div><!--/span-->
        <div class="span6">
            <h2><@spring.message "monitoring.inf.serv_detail" /></h2>
            <p><@spring.message "monitoring.inf.serv_detail.text" /></p>
        </div><!--/span-->
        <hr>
    </div><!--/row-->

    <#-- ===================== Icon Information row  ========================= -->

    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "monitoring.icon_inf.state_icon" /></h3>
            <p><i class="icon-ok" ></i>&nbsp;- <@spring.message "monitoring.icon_inf.ok" /></P>
            <p><i class="icon-warning-sign" ></i>&nbsp;- <@spring.message "monitoring.icon_inf.warn" /></P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- <@spring.message "monitoring.icon_inf.circle" /></P>
        </div><!--/span-->
        <div class="span6">
            <h3><@spring.message "monitoring.icon_inf.active" /></h3>
            <p><i class="icon-thumbs-up" ></i>&nbsp;- <@spring.message "monitoring.icon_inf.thumbs-up" /></P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- <@spring.message "monitoring.icon_inf.ban-circle" /></P>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>