<#-- ==============================================================
     ADMIN layout
        - for administrative pages
        - with access ROLE_ADMIN
     ============================================================== -->
<#macro page title>
<#include "../common/header.ftl"/>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <#include "../common/header/admin_sidebar1.ftl"/>
            </div>
            <div class="span8">
                <#nested/>
            </div>
        </div><!--/row-->
    </div><!--/container-->
<#include "../common/footer.ftl"/>
</#macro>