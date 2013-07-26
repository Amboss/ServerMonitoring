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
    <div class="hero-unit">
        <h1>Server monitoring</h1>
    </div>
    <div class="row-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Server name</th>
                    <th>State</th>
                    <th>Last check</th>
                    <th>Active</th>
                </tr>
            </thead>
            <#if availableServers?has_content>
                <#list availableServers as server >
                    <#assign serverName = server.server_name />
                    <tbody>
                        <tr>
                            <td><a href="../employee/serv_details/${serverName}.html">${serverName}<a/></td>
                            <td>${server.state}</td>
                            <td>${server.lastCheck}</td>
                            <td>${server.active}</td>
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
</@com.page>