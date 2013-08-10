<#-- =====================================================================
     Employee manager page
        - table contains links to update & delete page
        - table excluding current user info
     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Employee manager">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Employee management</h2>
    </div>

     <#-- ===================== Main row for Employee manager table ========================= -->
    <div class="row-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Active</th>
                    <th>lastLogin</th>
                    <th>Actions</th>
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
                                <i class="icon-edit" ></i>&nbsp;Edit
                            <a/>
                        </td>
                        <td><a href="<@spring.url '/employee_management/serv_assignment/${id}' />">
                               <i class="icon-check" ></i>&nbsp;Assign
                            <a/>
                        </td>
                        <td>
                            <a href="<@spring.url '/employee_management/employee_removal/${id}' />">
                                <i class="icon-trash" ></i>&nbsp;Delete
                            <a/>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <#-- ===================== Information row for Employee manager page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>List of employee</h3>
            <p>List on this page shows all employee with short information
            for general control.</p>
            <p><i class="icon-thumbs-up" ></i>&nbsp;- icon shows that employee access is granted </P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- icon shows that employee access is denied</P>

        </div><!--/span-->
        <div class="span6">
            <h3>Registration of new employee</h3>
            <p>You can register a new employee by clicking "Create" button, this will redirect you
            to registration form.</p>
            <p><a class="btn" href="<@spring.url '/employee_management/employee_registr' />">
                    Create new employee &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>