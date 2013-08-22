<#-- =====================================================================
     Server registration page

     ===================================================================== -->

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign pageTitle><@spring.message "settings.title" /></#assign>

<@com.page title="${pageTitle}">
<#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Main row for Registration form ========================= -->
    <div class="span12">
        <form id="employeeRegistrationForm"
                class="form-horizontal"
                method="post"
                autocomplete="off" >
            <h3><@spring.message "serv_registr.main_row.title" />:</h3>

            <#-- ===================== Server Name ========================= -->
            <div class="control-group info">
                <label class="control-label" for="server_name">
                    <@spring.message "serv_registr.main_row.name" /></label>
                <div class="controls">
                   <@spring.formInput "newServer.server_name", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server address ========================= -->
            <div class="control-group info">
                <label class="control-label" for="address">
                    <@spring.message "serv_registr.main_row.address" /></label>
                <div class="controls">
                   <@spring.formInput "newServer.address", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server port ========================= -->
            <div class="control-group info">
                <label class="control-label" for="port">
                    <@spring.message "serv_registr.main_row.port" /></label>
                <div class="controls">
                   <@spring.formInput "newServer.port", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Server url ========================= -->
            <div class="control-group info">
                <label class="control-label" for="url">
                    <@spring.message "serv_registr.main_row.url" /></label>
                <div class="controls">
                   <@spring.formInput "newServer.url", "input-xlarge" />
                   <@spring.showErrors " ", "alert alert-error"/>
                </div>
            </div>

            <#-- ===================== Active ========================= -->
            <div class="control-group info">
                <label class="control-label" for="activeState">
                    <@spring.message "serv_registr.main_row.active" /></label>
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
                            value='<@spring.message "serv_registr.btn_create" />'/>
                    <input class="btn"
                            type='submit'
                            name='cancel'
                            value='<@spring.message "serv_registr.btn_cancel" />'/>
                </div>
            </div>
        </form>
    </div><!--/row-->

    <#-- ===================== Information row for Registration page ========================= -->
    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "serv_registr.inf_specific.title" /></h3>
            <p><@spring.message "serv_registr.inf_specific.text" /></p>
        </div><!--/span-->
        <div class="span6">
            <h3><@spring.message "serv_registr.inf_name.title" /></h3>
            <p><@spring.message "serv_registr.inf_name.text" /></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>