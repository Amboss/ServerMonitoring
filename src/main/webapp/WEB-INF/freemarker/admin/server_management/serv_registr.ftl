<#-- =====================================================================
     Server registration page

     ===================================================================== -->

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#import "/util/spring.ftl" as spring />
<#import "/layout/admin.ftl" as com>

<@com.page title="Server registration">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Registration of new employee</h2>
    </div>

    <#-- ===================== Main row for Registration form ========================= -->
    <div class="span12">
        <form id="employeeRegistrationForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3>Please fill in required information:</h3>

            <#-- ===================== Server Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="server_name">Name</label>
                <div class="controls">
                   <@spring.formInput "newServer.server_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="address">Address</label>
                <div class="controls">
                   <@spring.formInput "newServer.address", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="port">Port</label>
                <div class="controls">
                   <@spring.formInput "newServer.port", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server url ========================= -->
            <div class="control-group info">
                <label class="control-label" for="url">URL</label>
                <div class="controls">
                   <@spring.formInput "newServer.url", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="activeState">Active status</label>
                <div class="controls">
                    <@spring.formSingleSelect "simplFormModel.activeState", activeMap, " "/>
                </div>
            </div>

            <#-- ===================== Buttons ================================== -->
            <div class="control-group">
                <div class="controls">
                    <input class="btn btn-primary"
                            type='submit'
                            name='create'
                            value='Create server'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='Cancel'/>
                </div>
            </div>
        </form>
    </div><!--/row-->

    <#-- ===================== Information row for Registration page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3>Registration specifications</h3>
            <p>To register a new server you have to specify related info such as Name,
            Address, Port (80 by default), URL("/" by default), Activation status. All
             other supported data will be generated automatically.</p>
        </div><!--/span-->
        <div class="span6">
            <h3>unique server name</h3>
            <p>After successful registration, system wil notify provided employee, by specified
             E-mail, with his new ability to access his workplace with new login and new password.</p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>