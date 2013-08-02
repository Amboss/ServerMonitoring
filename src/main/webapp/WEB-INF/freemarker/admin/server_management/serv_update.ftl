<#-- =====================================================================
     Server update page

     ===================================================================== -->
<#import "/layout/admin.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Server update">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Server update</h2>
    </div>

    <#-- ===================== Main row for update form ========================= -->
    <div class="span12">
        <form id="employeeEmployeeUpdateForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3>Please fill in required information:</h3>

            <#-- ===================== Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="server_name">Server name</label>
                <div class="controls">
                   <@spring.formInput "serverEntity.server_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="address">Address</label>
                <div class="controls">
                   <@spring.formInput "serverEntity.address", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="port">Port</label>
                <div class="controls">
                   <@spring.formInput "serverEntity.port", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== URL ========================= -->
            <div class="control-group info">
                <label class="control-label" for="url">URL</label>
                <div class="controls">
                   <@spring.formInput "serverEntity.url", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="state">Active</label>
                <div class="controls">
                    <@spring.formSingleSelect "activeState.state", activeMap, " "/>
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