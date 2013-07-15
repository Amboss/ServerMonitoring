<#-- =====================================================================
     Password update page
        - give ability for user to change existing password
        - for admin_role the layout wil contain service menu on left side bar
     ===================================================================== -->
<#import "/spring.ftl" as spring />
<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />
<#assign form=JspTaglibs["/WEB-INF/tlds/spring-form.tld"] />

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
        <form class="form-horizontal" method="post" autocomplete="off" >
            <h3>Please enter required information:</h3>
            <#-- ===================== Current Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="current_password">Current Password</label>
                <div class="controls">
                    <input class="input-xlarge"
                            name='currentPassword'
                            type='password'
                            autofocus/>
                    <#if RequestParameters['currentPassword']??>
                        <span class="help-inline alert alert-error" path="currentPassword">
                            <@spring.message "<br>", "errors"/>
                        </span>
                    </#if>
                </div>
            </div>
            <#-- ===================== New Password ============================= -->
            <div class="control-group info">
                <label class="control-label" for='new_password'>New Password</label>
                <div class="controls">
                    <input class="input-xlarge"
                            name='newPassword'
                            type='password' />
                    <#if RequestParameters['newPassword']??>
                        <span class="help-inline alert alert-error" path="newPassword">
                            <@spring.message "<br>", "errors"/>
                        </span>
                    </#if>
                </div>
            </div>
            <#-- ===================== Confirm Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="confirm_password">Confirm Password</label>
                <div class="controls">
                    <input class="input-xlarge"
                            name='confirmPassword'
                            type='password' />
                    <#if RequestParameters['confirmPassword']??>
                        <span class="help-inline alert alert-error" path="confirmPassword">
                            <@spring.message "<br>", "errors"/>
                        </span>
                    </#if>
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
                            type='reset'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
    </div><!--/row-->
</@com.page>