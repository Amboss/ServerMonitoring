<#-- =====================================================================
     Server Monitoring page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
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
        <table>
            <tr>
                <th>Server name</th>
                <th>Address</th>
                <th>Port</th>
                <th>URL</th>
                <th>State</th>
                <th>Response</th>
                <th>Created</th>
                <th>Last check</th>
                <th>Active</th>
            </tr>
            <#--list ServerEntity as ServerEntity-->
                <tr>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                    <td>_ _ _ _ _ _</td>
                </tr>
            <#--/#list-->
        </table>
    </div>
</@com.page>