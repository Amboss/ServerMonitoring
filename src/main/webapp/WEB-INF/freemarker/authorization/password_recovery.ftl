<#-- =====================================================================
     Password recovery page
        - give ability to recover password if user profile is active in DB
        - show error messages
        - on "cancel" redirect ot index page
     ===================================================================== -->
<#import "/spring.ftl" as spring />

<#import "/layout/default.ftl" as com />

<@spring.bind "passRecovery" />

<#assign pageTitle><@spring.message "index.title" /></#assign>

<@com.page title="${pageTitle}">

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h1>${pageTitle}</h1>
    </div>
    <div class="row-fluid">

        <#-- ===================== Left row for messages ========================= -->
        <div class="span6">
            <h2><@spring.message "pass_recovery.forgot.title" /></h2>
            <p><@spring.message "pass_recovery.forgot.text" /></p>
        </div><!--/span-->

        <#-- ===================== Right row for E-mail form ========================= -->
        <div class="span6">
            <form id="passRecoveryForm"
                    class="form-horizontal"
                    method="post"
                    autocomplete="off" >
                <h2><@spring.message "pass_recovery.email.title" /></h2>

                <#-- ===================== Employee E-mail ========================= -->
                <div class="control-group info">
                    <p><@spring.message "pass_recovery.email.text" /></p>
                    <@spring.formInput "passRecovery.email", "placeholder='E-mail'"/>
                    <@spring.showErrors " ", "alert alert-error"/>
                </div>

                <#-- ===================== Buttons ================================== -->
                <div class="control-group">
                    <input class="btn btn-primary"
                            type='submit'
                            name='Generate password'
                            value='<@spring.message "pass_recovery.form.btn_generate" />' />
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "pass_recovery.form.btn_cancel" />'  />
                </div>
            </form>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>