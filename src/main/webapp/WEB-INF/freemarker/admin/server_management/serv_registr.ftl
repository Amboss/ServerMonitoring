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
                <label class="control-label" for="server_name">Server name</label>
                <div class="controls">
                   <@spring.formInput "newServer.server_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="address">Server address</label>
                <div class="controls">
                   <@spring.formInput "newServer.address", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="port">Server port</label>
                <div class="controls">
                   <@spring.formInput "newServer.port", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server url ========================= -->
            <div class="control-group info">
                <label class="control-label" for="url">Server url</label>
                <div class="controls">
                   <@spring.formInput "newServer.url", "input-xlarge" />
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
                            name='create'
                            value='Create employee'/>
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
            <p>To register a new employee you have to specify related info such as Name,
            Last name, Login, Activation status, Security level. All other supported data will
            be generated automatically.</p>
        </div><!--/span-->
        <div class="span6">
            <h3>Informing new Employee</h3>
            <p>After successful registration, system wil notify provided employee, by specified
             E-mail, with his new ability to access his workplace with new login and new password.</p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>