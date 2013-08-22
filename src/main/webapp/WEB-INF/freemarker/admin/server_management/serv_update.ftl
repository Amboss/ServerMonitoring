<#-- =====================================================================
     Server update page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "server_update.title" /></#assign>

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
            <h3><@spring.message "server_update.main_row.title" />:</h3>
            <hr></br>

            <#-- ===================== Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="server_name">
                    <@spring.message "server_update.serv_name" /></label>
                <div class="controls">
                   <@spring.formInput "serverEntity.server_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="address">
                    <@spring.message "server_update.address" /></label>
                <div class="controls">
                   <@spring.formInput "serverEntity.address", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="port">
                    <@spring.message "server_update.port" /></label>
                <div class="controls">
                   <@spring.formInput "serverEntity.port", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== URL ========================= -->
            <div class="control-group info">
                <label class="control-label" for="url">
                    <@spring.message "server_update.url" /></label>
                <div class="controls">
                   <@spring.formInput "serverEntity.url", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="activeState">
                    <@spring.message "server_update.active" /></label>
                 <div class="controls">
                    <@spring.formSingleSelect "simplFormModel.activeState", activeMap, " "/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='update'
                            value='<@spring.message "server_update.btn_save" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "server_update.btn_cancel" />'/>
                </div>
            </div>
        </form>
        </br><hr>
    </div><!--/row-->

    <#-- ===================== Information row for Update page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "server_update.info.edit" /></h3>
            <p><@spring.message "server_update.info.edit_text" /></p>
        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i><@spring.message "server_update.info.status" /></h3>
            <p><@spring.message "server_update.info.status_text" /></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>