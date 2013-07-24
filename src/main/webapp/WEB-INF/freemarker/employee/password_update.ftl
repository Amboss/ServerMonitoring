<#-- =====================================================================
     Password update page
        - give ability for user to change existing password
        - for admin_role the layout wil contain service menu on left side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />
<@spring.bind "passUpdate" />

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>

<@com.page title="Password change">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h1>Password update</h1>
    </div>

    <#-- ===================== Main row for Password update form ========================= -->
    <div class="row-fluid">
        <form id="passUpdateForm" class="form-horizontal" method="post" autocomplete="off" >
            <h3>Please fill in required information:</h3>

            <#-- ===================== Current Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="current_password">Current Password</label>
                <div class="controls">
                   <@spring.formPasswordInput "passUpdate.currentPassword", "input-xlarge"/>
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== New Password ============================= -->
            <div class="control-group info">
                <label class="control-label" for='new_password'>New Password</label>
                <div class="controls">
                    <@spring.formPasswordInput "passUpdate.newPassword", "input-xlarge"/>
                    <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Confirm Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="confirm_password">Confirm Password</label>
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
                            value='Change password'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
    </div><!--/row-->
</@com.page>