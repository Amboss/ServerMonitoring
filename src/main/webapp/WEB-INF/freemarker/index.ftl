<#-- =====================================================================
     Index page
        - will appear as start page with authentication purpose
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />
<#import "/layout/default.ftl" as com>

<#assign pageTitle><@spring.message "index.title" /></#assign>
<@com.page title="${pageTitle}">
    <div class="hero-unit">
        <h1>${pageTitle}</h1>

    </div>
    <div class="row-fluid">
        <div class="span6">
            <h2><@spring.message "index.please_log" /></h2>
            <p><@spring.message "index.please_log.text" /></p>

            <#include "common/message/login_message.ftl"/>

        </div><!--/span-->
        <div class="span6">
            <#import "/util/spring.ftl" as spring /><!-- make message work -->
            <h2><@spring.message "index.pass_rec" /></h2>
            <p><@spring.message "index.pass_rec.text" /></p>

            <p><a class="btn" href="password_recovery"><@spring.message "index.pass_rec.btn" />&raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>