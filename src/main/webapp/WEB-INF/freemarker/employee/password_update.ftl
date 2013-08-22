<#-- =====================================================================
     Password update page
        - give ability for user to change existing password
        - for admin_role the layout wil contain service menu on left side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@spring.bind "passUpdate" />

<#assign pageTitle><@spring.message "serv_detail.title" /></#assign>

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>

<@com.page title="${pageTitle}">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for Password update form ========================= -->
    <div class="row-fluid">
        <form id="passUpdateForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3><@spring.message "pass_update.fill_in" />:</h3>

            <#-- ===================== Current Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="currentPassword"><@spring.message "pass_update.currentPassword" /></label>
                <div class="controls">
                   <@spring.formPasswordInput "passUpdate.currentPassword", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== New Password ============================= -->
            <div class="control-group info">
                <label class="control-label" for='newPassword'><@spring.message "pass_update.newPassword" /></label>
                <div class="controls">
                    <@spring.formPasswordInput "passUpdate.newPassword", "input-xlarge"/>
                    <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Confirm Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="confirmPassword"><@spring.message "pass_update.confirmPassword" /></label>
                <div class="controls">
                    <@spring.formPasswordInput "passUpdate.confirmPassword", "input-xlarge"/>
                    <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='update'
                            value='<@spring.message "pass_update.btn.update" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "pass_update.btn.cancel" />'/>
                </div>
            </div>
        </form>
    </div><!--/row-->
</@com.page>