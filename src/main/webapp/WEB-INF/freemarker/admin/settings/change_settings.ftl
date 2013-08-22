<#-- =====================================================================
     System settings page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "settings.title" /></#assign>

<@com.page title="${pageTitle}">

<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>System settings</h2>
    </div>

    <#-- ===================== Main row for update form ========================= -->
    <div class="span12">
        <form id="systemSettingsForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >

            <#-- passing id and name -->
            <@spring.formHiddenInput "settings.id"/>
            <@spring.formHiddenInput "settings.settings_name"/>

            <h3><@spring.message "settings.current.title" />: ${settings.settings_name}</h3>
            <hr>

            <#-- ===================== Server Scan Interval ========================= -->
            <div class="control-group info">
                <label class="control-label" for="serverScanInterval">
                    <@spring.message "settings_label.scan_interval" /></label>
                <div class="controls">
                   <@spring.formInput "settings.serverScanInterval", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Respond timeout ========================= -->
            <div class="control-group info">
                <label class="control-label" for="timeoutOfRespond">
                    <@spring.message "settings_label.respond" /></label>
                <div class="controls">
                   <@spring.formInput "settings.timeoutOfRespond", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Page Reload Time ========================= -->
            <div class="control-group info">
                <label class="control-label" for="pageReloadTime">
                    <@spring.message "settings_label.reload" /></label>
                <div class="controls">
                   <@spring.formInput "settings.pageReloadTime", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Server Host ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerHost">
                    <@spring.message "settings_label.host" /></label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerHost", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Server Port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerPort">
                    <@spring.message "settings_label.port" /></label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerPort", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Username ========================= -->
            <div class="control-group info">
                <label class="control-label" for="username">
                    <@spring.message "settings_label.username" /></label>
                <div class="controls">
                   <@spring.formInput "settings.username", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="password">
                    <@spring.message "settings_label.pass" /></label>
                <div class="controls">
                   <@spring.formInput "settings.password", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='update'
                            value='<@spring.message "settings_label.btn_save" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "settings_label.btn_cancel" />'/>
                </div>
            </div>
        </form>
        <hr>
    </div><!--/row-->

    <#-- ===================== Information row for Update page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "settings.inf.title" /></h3>
            <p><@spring.message "settings.inf.list_text" />: </p>
            <ul>
                <li><@spring.message "settings.inf.scan_int" /></li>
                <li><@spring.message "settings.inf.respond" /></li>
                <li><@spring.message "settings.inf.reload" /></li>
                <li><@spring.message "settings.inf.host" /></li>
                <li><@spring.message "settings.inf.port" /></li>
                <li><@spring.message "settings.inf.username" /></li>
                <li><@spring.message "settings.inf.pass" /></li>
            </ul>
        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i><@spring.message "settings.notice.title" /></h3>
            <p><@spring.message "settings.notice.text" /></p>
            <p><@spring.message "settings.notice.text_notice" /></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>