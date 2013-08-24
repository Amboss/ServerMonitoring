<#-- =====================================================================
     Server manager page
        - table contains links to update & delete page
     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "serv_mager.title" /></#assign>

<@com.page title="${pageTitle}">

    <#-- ===================== JQuery functions ========================= -->
     <#assign reloadTime = tableReloadTime />
     <#include "/util/js/reloadTable.ftl"/>

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

     <#-- ===================== Main row for Server manager table ========================= -->
    <div class="row-fluid">
        <h3><@spring.message "serv_mager.main_row.title" /></h3>
        <hr></br>

        <table cellpadding="0"
                cellspacing="0"
                border="0"
                id="serverManager"
                class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th><@spring.message "serv_mager.table.address_port" /></th>
                    <th><@spring.message "serv_mager.table.response" /></th>
                    <th><@spring.message "serv_mager.table.last_check" /></th>
                    <th><@spring.message "serv_mager.table.active" /></th>
                    <th><@spring.message "serv_mager.table.actions" /></th>
                </tr>
            </thead>
            <#list server as server >
            <#assign id = server.id />

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
                        <td>${server.server_name}:${server.port}<a/></td>
                        <td>${responseIcon}</td>
                        <td>${server.lastCheck}</td>
                        <td>${activeIcon}</td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_update/${id}.html' />">
                                <i class="icon-edit" ></i>&nbsp;<@spring.message "serv_mager.table_edit" />
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/server_management/serv_removal/${id}.html' />">
                                <i class="icon-trash" ></i>&nbsp;<@spring.message "serv_mager.table_selete" />
                            <a/>
                        </td>
                    </tr>
                </tbody>
            </#list>
        </table>
        </br><hr>
    </div>

    <#-- ===================== Information row for Server manager page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "serv_mager.inf.list.title" /></h3>
            <p><@spring.message "serv_mager.inf.list.text" /></p>
        </div><!--/span-->
        <div class="span6">
            <h3><@spring.message "serv_mager.inf.registr.title" /></h3>
            <p><@spring.message "serv_mager.inf.registr.text" /></p>
            <p><a class="btn" href="<@spring.url '/server_management/serv_registr' />">
                    <@spring.message "serv_mager.inf.btn" />&raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->

    <#-- ===================== Icon Information row  ========================= -->

        <div class="row-fluid">
            <div class="span6">
                <h3><@spring.message "serv_mager.resp_icon.title" /></h3>
                <p><i class="icon-ok" ></i>&nbsp;- <@spring.message "serv_mager.resp_icon.ok" /></P>
                <p><i class="icon-warning-sign" ></i>&nbsp;- <@spring.message "serv_mager.resp_icon.war" /></P>
                <p><i class="icon-ban-circle" ></i>&nbsp;- <@spring.message "serv_mager.resp_icon.fail" />r</P>
            </div><!--/span-->
            <div class="span6">
                <h3><@spring.message "serv_mager.activ_icon.title" /></h3>
                <p><i class="icon-thumbs-up" ></i>&nbsp;- <@spring.message "serv_mager.activ_icon.thumbs" /></P>
                <p><i class="icon-ban-circle" ></i>&nbsp;- <@spring.message "serv_mager.activ_icon.circul" /></P>
            </div><!--/span-->
        </div><!--/row-->
</@com.page>