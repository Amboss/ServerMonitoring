<#-- =====================================================================
     Server details page
        - give ability for user to to see precise info about specific server
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

<@com.page title="Server details">

    <#assign reloadTime = 5000 />
    <#include "/util/js/reloadTable.ftl"/>

    <div class="hero-unit">
        <h1>Server details page</h1>
    </div>
    <div class="row-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Server name</th>
                    <th>Address & Port</th>
                    <th>URL</th>
                    <th>State</th>
                    <th>Last check</th>
                    <th>Response</th>
                </tr>
            </thead>
                <tbody>
                    <tr>
                        <td>${server.server_name}</td>
                        <td>${server.address}:${server.port}</td>
                        <td>${server.url}</td>
                        <td>${server.state}</td>
                        <td>${server.lastCheck!}</td>
                        <td>${server.response}</td>
                    </tr>
                </tbody>
        </table>
    </div>
</@com.page>
