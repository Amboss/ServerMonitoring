<#-- =====================================================================
     Employee update page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "empl_update.title" /></#assign>

<@com.page title="${pageTitle}">
    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for update form ========================= -->
    <div class="span12">
        <form id="employeeEmployeeUpdateForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3><@spring.message "empl_update.main_row.title" />:</h3>
            <hr>
            <#-- ===================== Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="employee_name">
                    <@spring.message "empl_update.name" /></label>
                <div class="controls">
                   <@spring.formInput "employeeEntity.employee_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== E-mail ========================= -->
            <div class="control-group info">
                <label class="control-label" for="email">
                    <@spring.message "empl_update.email" /></label>
                <div class="controls">
                   <@spring.formInput "employeeEntity.email", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="activeState">
                    <@spring.message "empl_update.active" /></label>
                <div class="controls">
                    <@spring.formSingleSelect "simplFormModel.activeState", activeMap, " "/>
                </div>
            </div>

            <#-- ===================== Buttons ======================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='update'
                            value='<@spring.message "empl_update.btn_save" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "empl_update.btn_cancel" />'/>
                </div>
            </div>
        </form>
        <hr>
    </div><!--/row-->

    <#-- ===================== Information row for Update page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "empl_update.inf_specific.title" /></h3>
            <p><@spring.message "empl_update.inf_specific.text" /></p>
        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i> <@spring.message "empl_update.inf_status.title" /></h3>
            <p><@spring.message "empl_update.inf_status.text" /></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>