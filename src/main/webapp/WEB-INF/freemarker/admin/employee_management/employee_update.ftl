<#-- =====================================================================
     Employee update page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Employee update">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Employee update</h2>
    </div>

    <#-- ===================== Main row for update form ========================= -->
    <div class="span12">
        <form id="employeeEmployeeUpdateForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3>Please fill in required information:</h3>

            <#-- ===================== Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="employee_name">Employee name</label>
                <div class="controls">
                   <@spring.formInput "employeeEntity.employee_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== E-mail ========================= -->
            <div class="control-group info">
                <label class="control-label" for="email">E-mail</label>
                <div class="controls">
                   <@spring.formInput "employeeEntity.email", "input-xlarge"/>
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

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='create'
                            value='Change'/>
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
        <div class="span6">
            <h3>Edit specifications</h3>
            <p>To edit employee you can change supplied info such as Name,
            E-mail or Activation status.</p>
        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i> Not Active status</h3>
            <p>If current active status set to "not active" all operations
                of provided employee will be terminated.</p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>