<#-- =====================================================================
     Server assignment page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@com.page title="Server assignment">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Server assignment</h2>
    </div>

    <#-- ===================== Row for assigned servers table ========================= -->
    <div class="row-fluid">
        <h3>Assigned servers for user ${employee.employee_name}:</h3>
        <table class="table table-bordered">
            <thead >
                <tr>
                    <th>Server name</th>
                    <th>State</th>
                    <th>Active</th>
                    <td>assignment</td>
                </tr>
            </thead>
            <#if assignedServers?has_content>
                <#list assignedServers as assigned >

                    <#-- ===================== Server state icons ========================= -->
                    <#if assigned.state = "OK">
                        <#assign stateIcon><i class="icon-ok" ></#assign>
                    <#elseif assigned.state = "WARN">
                        <#assign stateIcon><i class="icon-warning-sign" ></#assign>
                    <#elseif assigned.state = "FAIL">
                        <#assign stateIcon><i class="icon-ban-circle" ></#assign>
                    </#if>

                    <#-- ===================== Server active icons ========================= -->
                    <#if assigned.active = 1>
                        <#assign activeIcon><i class="icon-thumbs-up" ></#assign>
                    <#elseif assigned.active = 0>
                        <#assign activeIcon><i class="icon-ban-circle" ></#assign>
                    </#if>

                    <#-- ===================== tbody ========================= -->
                    <#assign unassignId = assigned.id />
                    <tbody>
                        <tr>
                            <td>${assigned.server_name}</td>
                            <td>${stateIcon}</td>
                            <td>${activeIcon}</td>
                            <td><a href="<@spring.url '/employee_management/serv_assignment/${unassignId}.html' />">
                                 <i class="icon-remove-circle" ></i>&nbsp;unassign<a/>
                            </td>
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

    <#-- ===================== Row for available servers table ========================= -->
        <div class="row-fluid">
        <h3>Available servers:</h3>
            <table class="table table-bordered">
                <thead >
                    <tr>
                        <th>Server name</th>
                        <th>State</th>
                        <th>Active</th>
                        <td>assignment</td>
                    </tr>
                </thead>
                <#if availableServers?has_content>
                    <#list availableServers as available >

                        <#-- ===================== Server state icons ========================= -->
                        <#if available.state = "OK">
                            <#assign stateIcon><i class="icon-ok" ></#assign>
                        <#elseif available.state = "WARN">
                            <#assign stateIcon><i class="icon-warning-sign" ></#assign>
                        <#elseif available.state = "FAIL">
                            <#assign stateIcon><i class="icon-ban-circle" ></#assign>
                        </#if>

                        <#-- ===================== Server active icons ========================= -->
                        <#if available.active = 1>
                            <#assign activeIcon><i class="icon-thumbs-up" ></#assign>
                        <#elseif available.active = 0>
                            <#assign activeIcon><i class="icon-ban-circle" ></#assign>
                        </#if>

                        <#-- ===================== tbody ========================= -->
                        <#assign assignId = available.id />
                        <tbody>
                            <tr>
                                <td>${available.server_name}</td>
                                <td>${stateIcon}</td>
                                <td>${activeIcon}</td>
                                <td><a href="<@spring.url '/employee_management/serv_assignment/${assignId}.html' />">
                                     <i class="icon-ok-circle" ></i>&nbsp;assign<a/>
                                </td>
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
                            <td>- - -</td>
                        </tr>
                    </tbody>
                </#if>
            </table>
        </div>

        <#-- ===================== Buttons ======================== -->
        <div class="control-group">
            <div class="controls">
                <input class="btn btn-primary"
                        type='submit'
                        name='update'
                        value='Assign'/>
                <input class="btn"
                        type='submit'
                        name='cancel'
                        value='Cancel'/>
            </div>
        </div>
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