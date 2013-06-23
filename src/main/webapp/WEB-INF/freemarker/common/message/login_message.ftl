<#-- ==============================================================
     common massage handler ftl
     ============================================================== -->
 <#import "/spring.ftl" as spring />
 <#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />
 <#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#macro formErrors>
<#assign formErrors><@form.errors path="*" /></#assign>
    <#if formErrors?has_content>
        <div id="errors">
            <@spring.message "admin.error.globalMessage" />
        </div>
    </#if>
</#macro>
<#if RequestParameters.login_error?exists>
    <font color="red">
        Your login attempt was not successful, try again.<BR>
        Reason: ${acegiException.message}
    </font>
</#if>
<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    <@spring.message "label.loginerror"/>: ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
</#if>
<#if RequestParameters['authfail']??>
    <div class="row">
        <div class="span12">
            <div class="alert alert-error">
                Login failed
            </div>
        </div>
    </div>
</#if>
<#if RequestParameters['accessdenied']??>
    <div class="row">
        <div class="span12">
            <div class="alert alert-error">
                Access denied
            </div>
        </div>
    </div>
</#if>
<#if RequestParameters['login']??>
    <div class="row">
        <div class="span12">
            <div class="alert alert-warning">
                You are not signed in, please sign in first.
            </div>
        </div>
    </div>
</#if>
<#if RequestParameters['loggedout']??>
    <div class="row">
        <div class="span12">
            <div class="alert alert-success">
                You have been logged out.
            </div>
        </div>
    </div>
</#if>
