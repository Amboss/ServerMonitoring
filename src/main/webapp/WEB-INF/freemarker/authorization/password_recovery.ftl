<#-- =====================================================================
     Password recovery
        - give ability to recover password if user profile is active in DB
     ===================================================================== -->

<#import "/layout/default.ftl" as com>

<@com.page title="Password recovery">
    <div class="hero-unit">
        <h1>Password recovery</h1>
        <#--include "common/message/login_message.ftl"/-->
    </div>
    <div class="row-fluid">
        <div class="span6">
            <h2>Forget your password?</h2>
            <p>On this page you can recover your password to enter to
                Server monitoring service. Your account have to be active
                 for successful recovery. If you don't receive any recovery
                 E-mail please contact.</p>
        </div><!--/span-->
        <div class="span6">
            <h2>Please enter your E-mail</h2>
            <p>A new password will be generated and sanded </br>
                to your E-mail if access is active.</p>
            <p><input class="span6" type="text" placeholder="E-mail" /></p>
            <p>
                <input class="btn" type='submit' value='Generate password' />
                <input class="btn" type='reset' value='Cancel' />
            </p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>