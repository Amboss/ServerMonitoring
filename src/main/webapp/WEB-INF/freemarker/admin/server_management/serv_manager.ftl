<#-- =====================================================================
     Server manager page
        - table contains links to update & delete page
     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Server manager">
 <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Employee management</h2>
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
                   <#assign stateIcon><i class="icon-ok" ></#assign>
               <#elseif server.response = "WARN">
                   <#assign stateIcon><i class="icon-warning-sign" ></#assign>
               <#elseif server.response = "FAIL">
                   <#assign stateIcon><i class="icon-ban-circle" ></#assign>
               </#if>

                <tbody>
                    <tr>
                        <td>${name}<a/></td>
                        <td>${activeIcon}</td>
                        <td>${server.lastCheck}</td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_update/${name}.html' />">
                                <i class="icon-edit" ></i>&nbsp;Edit
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_update/${name}.html' />">
                               <i class="icon-check" ></i>&nbsp;Assign
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_removal.ftl/${name}.html' />">
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
            <p><a class="btn" href="<@spring.url '/admin/server_management/serv_registr.ftl' />">
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