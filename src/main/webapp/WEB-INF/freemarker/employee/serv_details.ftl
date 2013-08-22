<#-- =====================================================================
     Server details page
        - give ability for user to to see precise info about specific server
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->

<#import "/util/spring.ftl" as spring />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#assign pageTitle><@spring.message "serv_detail.title" /></#assign>

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>

<@com.page title="${pageTitle}">

    <#assign reloadTime = 5000 />
    <#include "/util/js/reloadTable.ftl"/>

    <div class="hero-unit">
        <h1>${pageTitle}</h1>
    </div>
    <div class="row-fluid">
         <table cellpadding="0"
                cellspacing="0"
                border="0"
                id="serverManager"
                class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th><@spring.message "serv_detail.table.serv_name" /></th>
                    <th><@spring.message "serv_detail.table.address" /></th>
                    <th><@spring.message "serv_detail.table.url" /></th>
                    <th><@spring.message "serv_detail.table.state" /></th>
                    <th><@spring.message "serv_detail.table.last_check" /></th>
                    <th><@spring.message "serv_detail.table.response" /></th>
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
