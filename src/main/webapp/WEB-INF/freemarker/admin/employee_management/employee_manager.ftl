<#-- =====================================================================
     Employee manager page
        - table contains links to update & delete page
        - table excluding current user info
     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "empl_registr.title" /></#assign>

<@com.page title="${pageTitle}">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for Employee manager table ========================= -->
    <div class="row-fluid">
         <h3><@spring.message "empl_manager.main_row.title" /></h3>
         <hr></br>

         <table cellpadding="0"
                cellspacing="0"
                border="0"
                id="serverManager"
                class="table table-striped table-bordered">
             <thead>
                 <tr>
                     <th><@spring.message "empl_manager.table_name" />Name</th>
                     <th><@spring.message "empl_manager.table_active" />Active</th>
                     <th><@spring.message "empl_manager.table_lastLogin" />lastLogin</th>
                     <th><@spring.message "empl_manager.table_actions" />Actions</th>
                 </tr>
             </thead>
             <tbody>
                 <#list employee as employee >
                     <#assign id = employee.id />

                     <#-- ===================== Employee active icons ========================= -->
                     <#if employee.active = 1>
                         <#assign activeIcon><i class="icon-thumbs-up" ></#assign>
                     <#elseif employee.active = 0>
                         <#assign activeIcon><i class="icon-ban-circle" ></#assign>
                     </#if>
                     <tr>
                         <td>${employee.employee_name}<a/></td>
                         <td>${activeIcon}</td>
                         <td>${employee.lastLogin}</td>
                         <td>
                             <a href="<@spring.url '/employee_management/employee_update/${id}' />">
                                 <i class="icon-edit" ></i>&nbsp;<@spring.message "empl_manager.icon_edit" /><a/>
                         </td>
                         <td><a href="<@spring.url '/employee_management/serv_assignment/${id}' />">
                                 <i class="icon-check" ></i>&nbsp;<@spring.message "empl_manager.icon_assign" /><a/>
                         </td>
                         <td>
                             <a href="<@spring.url '/employee_management/employee_removal/${id}' />">
                                 <i class="icon-trash" ></i>&nbsp;<@spring.message "empl_manager.icon_delete" /><a/>
                        </td>
                     </tr>
                 </#list>
             </tbody>
        </table>
        </br><hr>
    </div>

    <#-- ===================== Information row for Employee manager page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "empl_manager.inf_list.title" /></h3>
            <p><@spring.message "empl_manager.inf_list.text" /></p>
            <p><i class="icon-thumbs-up" ></i>&nbsp;- <@spring.message "empl_manager.inf_list.thumb" /></P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- <@spring.message "empl_manager.inf_list.circul" /></P>

        </div><!--/span-->
        <div class="span6">
            <h3><@spring.message "empl_manager.inf_registr.titl" /></h3>
            <p><@spring.message "empl_manager.inf_registr.text" /></p>
            <p><a class="btn" href="<@spring.url '/employee_management/employee_registr' />">
                    <@spring.message "empl_manager.btn_registr" /> &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>