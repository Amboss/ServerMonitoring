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
                    <th>Actions</th>
                </tr>
            </thead>
            <#list employee as employee >
            <#assign id = employee.id />
                <tbody>
                    <tr>
                        <td>${employee.employee_name}<a/></td>
                        <td>${employee.active}</td>
                        <td>${employee.lastLogin}</td>
                        <td>
                            <a href="<@spring.url '/employee_management/employee_update/${id}.html' />">
                                <i class="icon-edit" ></i>&nbsp;Edit
                            <a/>
                        </td>
                        <td><a href="<@spring.url '/employee_management/employee_removal/${id}.html' />">
                               <i class="icon-check" ></i>&nbsp;Assign
                            <a/>
                        </td>
                    </tr>
                </tbody>
            </#list>
        </table>
    </div>

    <#-- ===================== Information row for Employee manager page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>List of employee</h3>
            <p>List on this page shows all employee with short information
            for general control.</p>
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