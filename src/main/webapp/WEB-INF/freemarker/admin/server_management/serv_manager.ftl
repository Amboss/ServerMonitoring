<#-- =====================================================================
     Server manager page
        - table contains links to update & delete page
     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Server manager">
 <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Server management</h2>
    </div>

     <#-- ===================== Main row for Server manager table ========================= -->
    <div class="row-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Address & Port</th>
                    <th>Response</th>
                    <th>LastCheck</th>
                    <th>Active</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <#list server as server >
            <#assign name = server.server_name />

                <#-- ===================== Server response icons ========================= -->
                <#if server.response = "OK">
                    <#assign responseIcon><i class="icon-ok" ></#assign>
                <#elseif server.response = "WARN">
                    <#assign responseIcon><i class="icon-warning-sign" ></#assign>
                <#elseif server.response = "FAIL">
                    <#assign responseIcon><i class="icon-ban-circle" ></#assign>
                </#if>

                <#-- ===================== Server active icons ========================= -->
                <#if server.active = 1>
                    <#assign activeIcon><i class="icon-thumbs-up" ></#assign>
                <#elseif server.active = 0>
                    <#assign activeIcon><i class="icon-ban-circle" ></#assign>
                </#if>

                <tbody>
                    <tr>
                        <td>${name}:${server.port}<a/></td>
                        <td>${responseIcon}</td>
                        <td>${server.lastCheck}</td>
                        <td>${activeIcon}</td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_update/${name}.html' />">
                                <i class="icon-edit" ></i>&nbsp;Edit
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/employee_management/serv_assignment/${name}.html' />">
                               <i class="icon-check" ></i>&nbsp;Assign
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_removal/${name}.html' />">
                                <i class="icon-trash" ></i>&nbsp;Delete
                            <a/>
                        </td>
                    </tr>
                </tbody>
            </#list>
        </table>
    </div>

    <#-- ===================== Information row for Server manager page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>List of servers</h3>
            <p>List on this page shows all servers with short information
            for general control.</p>
        </div><!--/span-->
        <div class="span6">
            <h3>Registration of new server</h3>
            <p>You can register a new server by clicking "Create" button, this will redirect you
            to registration form.</p>
            <p><a class="btn" href="<@spring.url '/server_management/serv_registr' />">
                    Create new server &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->

    <#-- ===================== Icon Information row  ========================= -->

        <div class="row-fluid">
            <div class="span6">
                <h3>Response icons</h3>
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