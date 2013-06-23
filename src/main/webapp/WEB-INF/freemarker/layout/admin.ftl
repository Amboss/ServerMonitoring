<#-- ==============================================================
     layout for administrative pages with access
     ROLE_ADMIN
     ============================================================== -->
<#macro page title>
<#include "../common/header.ftl"/>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span4">
                <!--Sidebar content-->
            </div>
            <div class="span8">
                <#nested/>
            </div>
        </div>
    </div>
<#include "../common/footer.ftl"/>
</#macro>