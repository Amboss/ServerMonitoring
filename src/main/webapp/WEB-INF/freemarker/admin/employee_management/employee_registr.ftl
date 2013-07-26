<#-- =====================================================================
     Password update page
        - give ability for user to change existing password
        - for admin_role the layout wil contain service menu on left side bar
     ===================================================================== -->

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#import "/util/spring.ftl" as spring />
<#import "/layout/admin.ftl" as com>




<@com.page title="Employee registration">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Registration of new employee</h2>
    </div>

    <#-- ===================== Main row for Registration form ========================= -->
    <div class="span12">
        <form id="employeeRegistrationForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3>Please fill in required information:</h3>


            <#-- ===================== Name, lastName ========================= -->
            <div class="control-group info">
                <label class="control-label" for="employee_name">Employee name</label>
                <div class="controls">
                   <@spring.formInput "newEmployee.employee_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Login ========================= -->
            <div class="control-group info">
                <label class="control-label" for="login">Login</label>
                <div class="controls">
                   <@spring.formInput "newEmployee.login", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== E-mail ========================= -->
            <div class="control-group info">
                <label class="control-label" for="email">E-mail</label>
                <div class="controls">
                   <@spring.formInput "newEmployee.email", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="active">Active</label>
                <div class="controls">
                    <@spring.formSingleSelect "activeState.state", activeMap, " "/>
                </div>
            </div>

            <#-- ===================== Security level ========================= -->
            <div class="control-group info">
                <label class="control-label" for="active">Security level</label>
                <div class="controls">
                    <@spring.formSingleSelect "activeState.level", adminMap, " "/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='create'
                            value='Create employee'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
    </div><!--/row-->

    <#-- ===================== Information row for Registration page ========================= -->
    <div class="row-fluid">
        <div class="row-fluid">
            <div class="span6">
                <h3>Registration specifications</h3>
                <p>To register a new employee you have to specify related info such as Name,
                Last name, Login, Activation status, Security level. All other supported data will
                be generated automatically.</p>
            </div><!--/span-->
            <div class="span6">
                <h3>Informing new Employee</h3>
                <p>After successful registration, system wil notify provided employee, by specified
                 E-mail, with his new ability to access his workplace with new login and new password.</p>
            </div><!--/span-->
        </div><!--/row-->
</@com.page>