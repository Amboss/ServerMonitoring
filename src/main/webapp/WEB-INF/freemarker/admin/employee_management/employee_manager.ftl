<#import "/layout/admin.ftl" as com>

<@com.page title="Employee manager">
    <div class="hero-unit">
        <h2>Employee management</h2>
    </div>
    <div class="row-fluid">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Active</th>
                    <th>lastLogin</th>
                    <th>- - -</th>
                    <th>- - -</th>
                </tr>
            </thead>
            <#list employee as employee >
                <tbody>
                    <tr>
                        <td>${employee.employee_name}<a/></td>
                        <td>${employee.active}</td>
                        <td>${employee.lastLogin}</td>
                        <td>edit/delete</td>
                        <td>Designate</td>
                    </tr>
                </tbody>
            </#list>
        </table>
    </div>
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
            <p><a class="btn" href="employee_registr.html">Create new employee &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>