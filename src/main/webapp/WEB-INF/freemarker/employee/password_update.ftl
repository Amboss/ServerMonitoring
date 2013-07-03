<#-- =====================================================================
     Password update page
        - give ability for user to change existing password
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@security.authorize ifAnyGranted="ROLE_USER">
    <#import "/layout/employee.ftl" as com>
</@security.authorize>
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    <#import "/layout/admin.ftl" as com>
</@security.authorize>
<@com.page title="Password change">
    <div class="hero-unit">
        <h1>Please update your password</h1>
        <#include "../common/message/validation_message.ftl"/>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <form action="updatePassword" id="passwordResetForm" autocomplete="off">
            <p>
            <span class='text_'>Please enter required information:</span>
            </p>
            <p>
            <label for='password'>Current UserName</label>
            <input class="span3" disabled name='username' value=${username} placeholder="username" />
            </p>
            <p>
            <label for='password'>Current Password</label>
            <input class="span3"  id="focusedInput" name='oldPassword' placeholder="old password" required/>
            </p>
            <p>
            <label for='password'>New Password</label>
            <input class="span3"  id="focusedInput" name='new_password' placeholder="new password" required/>
            </p>
            <p>
            <label for='password'>New Password (again)</label>
            <input class="span3"  id="focusedInput" name='new_password2' placeholder="new password" required/>
            </p>
            <p>
            <input class="btn" type='submit' value='Change password' /><input class="btn" type='reset' value='Cancel' />
            </p>
            </form>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>