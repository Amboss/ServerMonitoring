<#-- ==============================================================
     EMPLOYEE layout
        - for employee pages
        - with access ROLE_USER
     ============================================================== -->
<#macro page title>
<#include "../common/header.ftl"/>
     <div class="container">
         <#nested/>
     </div>
<#include "../common/footer.ftl"/>
</#macro>