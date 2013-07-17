<#-- =====================================================================
     Password recovery page
        - give ability to recover password if user profile is active in DB
        - show error messages
        - on "cancel" redirect ot index page
     ===================================================================== -->
<#import "/spring.ftl" as spring />
<#import "/layout/default.ftl" as com />
<@spring.bind "passRecovery" />

<@com.page title="Password recovery">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h1>Password recovery</h1>
    </div>
    <div class="row-fluid">

        <#-- ===================== Left row for messages ========================= -->
        <div class="span6">
            <h2>Forgot your password?</h2>
            <p>On this page you can recover your password to enter to
                Server monitoring service. Your account have to be active
                 for successful recovery. If you don't receive any recovery
                 E-mail please contact us.</p>
        </div><!--/span-->

        <#-- ===================== Right row for E-mail form ========================= -->
        <div class="span6">
            <form id="passRecoveryForm"
                    class="form-horizontal"
                    method="post"
                    autocomplete="off" >
                <h2>Please enter your E-mail</h2>

                <#-- ===================== Employee E-mail ========================= -->
                <div class="control-group info">
                    <p>A new password will be generated and will be sent to your E-mail </br>
                    if access is granted.</p>
                    <@spring.formInput "passRecovery.email", "placeholder='E-mail'"/>
                    <@spring.showErrors " ", 'alert alert-error'/>
                </div>

                <#-- ===================== Buttons ================================== -->
                <div class="control-group">
                    <input class="btn btn-primary"
                            type='submit'
                            name='Generate password'
                            value='Generate Password' />
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'  />
                </div>
            </form>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>