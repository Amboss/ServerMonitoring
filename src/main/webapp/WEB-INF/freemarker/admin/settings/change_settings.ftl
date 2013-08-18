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
            <@spring.formHiddenInput "settings.id"/>
            <@spring.formHiddenInput "settings.settings_name"/>

            <h3>Current settings: ${settings.settings_name}</h3>
            <hr>

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

            <#-- ===================== SMTP Server Host ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerHost">SMTP Server Host</label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerHost", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Server Port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="smtpServerPort">SMTP Server Port</label>
                <div class="controls">
                   <@spring.formInput "settings.smtpServerPort", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Username ========================= -->
            <div class="control-group info">
                <label class="control-label" for="username">SMTP Username</label>
                <div class="controls">
                   <@spring.formInput "settings.username", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== SMTP Password ========================= -->
            <div class="control-group info">
                <label class="control-label" for="password">SMTP Password</label>
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
                            value='Save changes'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
        <hr>
    </div><!--/row-->

    <#-- ===================== Information row for Update page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>Edit settings</h3>
            <p>To edit server you can change supplied info such as: </p>
            <ul>
                <li>Scan Interval - digits in seconds</li>
                <li>Respond timeout - digits in seconds</li>
                <li>Page Reload Time - digits in seconds</li>
                <li>SMTP Host - mail server host address</li>
                <li>SMTP Port - digits</li>
                <li>SMTP UserName - name of mail account</li>
                <li>SMTP Password - for mail account</li>
            </ul>

        </div><!--/span-->
        <div class="span6">
            <h3><i class="icon-warning-sign" ></i>Notice</h3>
            <p>Please be sure to use digits for Interval, Timeout and Reload.</p>
            <p>Notice, that 1sec = 1000.</p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>