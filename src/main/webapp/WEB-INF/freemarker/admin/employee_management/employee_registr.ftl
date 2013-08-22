<#-- =====================================================================
     Employee registration page

     ===================================================================== -->

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign pageTitle><@spring.message "empl_registr.title" /></#assign>

<@com.page title="${pageTitle}">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for Registration form ========================= -->
    <div class="span12">
        <form id="employeeRegistrationForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3><@spring.message "empl_registr.main_row.title" />:</h3>
            <hr>

            <#-- ===================== Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="employee_name">
                    <@spring.message "empl_registr.name" /></label>
                <div class="controls">
                   <@spring.formInput "newEmployee.employee_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Login ========================= -->
            <div class="control-group info">
                <label class="control-label" for="login">
                    <@spring.message "empl_registr.login" /></label>
                <div class="controls">
                   <@spring.formInput "newEmployee.login", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== E-mail ========================= -->
            <div class="control-group info">
                <label class="control-label" for="email">
                    <@spring.message "empl_registr.email" /></label>
                <div class="controls">
                   <@spring.formInput "newEmployee.email", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="activeState">
                    <@spring.message "empl_registr.active" /></label>
                <div class="controls">
                    <@spring.formSingleSelect "simplFormModel.activeState", activeMap, " "/>
                </div>
            </div>

            <#-- ===================== Security level ========================= -->
            <div class="control-group info">
                <label class="control-label" for="level">
                    <@spring.message "empl_registr.secur" /></label>
                <div class="controls">
                    <@spring.formSingleSelect "simplFormModel.level", adminMap, " "/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='create'
                            value='<@spring.message "empl_registr.btn_create" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "empl_registr.btn_cancel" />'/>
                </div>
            </div>
        </form>
        <hr>
    </div><!--/row-->

    <#-- ===================== Information row for Registration page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "empl_registr.inf_registr.title" /></h3>
            <p><@spring.message "empl_registr.inf_registr.title" /></p>
        </div><!--/span-->
        <div class="span6">
            <h3><@spring.message "empl_registr.inf_inform.title" /></h3>
            <p><@spring.message "empl_registr.inf_inform.title" /></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>