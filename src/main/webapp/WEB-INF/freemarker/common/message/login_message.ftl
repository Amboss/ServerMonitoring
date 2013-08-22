<#-- ==============================================================
     login massage handler
     ============================================================== -->
<#import "/util/spring.ftl" as spring />

<#assign spring=JspTaglibs["/WEB-INF/tlds/spring.tld"] />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    <div class="alert alert-error">
         <p>${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
    </div>
</#if>

