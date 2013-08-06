<#-- =====================================================================
     Server Monitoring page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>

<@com.page title="Monitoring service">
    <#-- ===================== reloading page ========================= -->
    <#assign reloadTime = 5000 />
    <#include "/util/reloadTable.ftl"/>

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h1>Server monitoring</h1>
    </div>

    <#-- ===================== Main row for Server Monitoring table ========================= -->
    <div class="row-fluid">
        <table id="monitoringTable" class="table table-bordered">
            <thead >
                <tr>
                    <th>Server name</th>
                    <th>State</th>
                    <th>Last check</th>
                    <th>Active</th>
                </tr>
            </thead>
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

                    <#-- ===================== tbody ========================= -->
                    <#assign serverName = server.server_name />
                    <tbody>
                        <tr>
                            <td><a href="<@spring.url '/employee/serv_details/${serverName}.html' />">
                                <i class="icon-search" ></i>&nbsp;${serverName}<a/></td>
                            <td style="text-align:center;">${stateIcon}</td>
                            <td>${server.lastCheck}</td>
                            <td>${activeIcon}</td>
                        </tr>
                    </tbody>
                </#list>
            <#else>
                <tbody>
                    <tr>
                        <td>- - -</td>
                        <td>- - -</td>
                        <td>- - -</td>
                        <td>- - -</td>
                    </tr>
                </tbody>
            </#if>
        </table>
    </div>

    <#-- ===================== Information row for Registration page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h2>List of servers</h2>
            <p>List on this page shows all provided servers with short information
            for general control.</p>
        </div><!--/span-->
        <div class="span6">
            <h2>Server details</h2>
            <p>You can get close look to certain server by clicking on his name, this will
            redirect you to server details page with more precise information of certain server.</p>
        </div><!--/span-->
    </div><!--/row-->

    <#-- ===================== Icon Information row  ========================= -->

    <div class="row-fluid">
        <div class="span6">
            <h3>State icons</h3>
            <p><i class="icon-ok" ></i>&nbsp;- icon shows that server have no errors</P>
            <p><i class="icon-warning-sign" ></i>&nbsp;- icon shows that server are working with HTTP
            response other than 200</P>
            <p><i class="icon-ban-circle" ></i>&nbsp;icon shows that server is not responding to request,
             or responding with HTTP 500 error</P>
        </div><!--/span-->
        <div class="span6">
            <h3>Active icons</h3>
            <p><i class="icon-thumbs-up" ></i>&nbsp;- icon shows that current server is under monitoring</P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- icon shows that current server is not under monitoring</P>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>