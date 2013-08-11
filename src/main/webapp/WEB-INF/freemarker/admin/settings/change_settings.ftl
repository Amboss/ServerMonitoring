<#-- =====================================================================
     System settings page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Settings">


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
            <@spring.formHiddenInput "settings.id", "input-xlarge" />
            <@spring.formHiddenInput "settings.settings_name", "input-xlarge" />

            <h3>Current settings: ${settings.settings_name}</h3>

            <#-- ===================== Server Scan Interval ========================= -->
            <div class="control-group info">
                <label class="control-label" for="serverScanInterval">Server Scan Interval</label>
                <div class="controls">
                   <@spring.formInput "settings.serverScanInterval", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Respond timeout ========================= -->
            <div class="control-group info">
                <label class="control-label" for="timeoutOfRespond">Respond timeout</label>
                <div class="controls">
                   <@spring.formInput "settings.timeoutOfRespond", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Page Reload Time ========================= -->
            <div class="control-group info">
                <label class="control-label" for="pageReloadTime">Page Reload Time</label>
                <div class="controls">
                   <@spring.formInput "settings.pageReloadTime", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== smtp Server Address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerAddress">SMTP Server Address</label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerAddress", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== smtp Server Port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerPort">SMTP Server Port</label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerPort", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='update'
                            value='Save changes'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
    </div><!--/row-->

    <#-- ===================== Information row for Update page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>Edit specifications</h3>
            <p>To edit server you can change supplied info such as Name,
            Address, Port, URL or Activation status.</p>
        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i>Active status</h3>
            <p>If current active status set to "not active" monitoring
                of provided server will be shut down.</p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>