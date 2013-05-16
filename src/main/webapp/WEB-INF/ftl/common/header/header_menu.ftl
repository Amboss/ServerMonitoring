[#ftl]
[#if Session.the_user?? && Session.the_user.loggedIn]
    [@security.authorize ifAnyGranted="ROLE_USER","ROLE_ADMIN" ]
            <a href="<@spring.url value="../employee/monitoring/password_update.ftl />" >Password change</a>
            <a href="<@spring.url value="../employee/monitoring/serv_details.ftl  />" >Server details</a>
            <a href="<@spring.url value="../employee/monitoring/monitoring.ftl />" >Monitoring service</a>
            <a href="<@spring.url value="../login.ftl />" >Login page</a>
            <a href="<@spring.url value="../authorization/pass_recovery.ftl />" >Login page</a>
    [/@security.authorize]

    [@security.authorize ifAnyGranted="ROLE_ADMIN"]
        <a href="<@spring.url value="../admin/settings/change_settings.ftl />" >Settings</a>
        <a href="<@spring.url value="../admin/server_management/serv_registr.ftl />" >Server registration</a>
        <a href="<@spring.url value="../admin/server_management/serv_update.ftl />" >Server update</a>
        <a href="<@spring.url value="../admin/server_management/serv_removal.ftl />" >Server removal</a>
        <a href="<@spring.url value="../admin/server_management/serv_manager.ftl />" >Server manager</a>
        <a href="<@spring.url value="../admin/employee_management/employee_registr.ftl />" >Employee registration</a>
        <a href="<@spring.url value="../admin/employee_management/employee_update.ftl />" >Employee update</a>
        <a href="<@spring.url value="../admin/employee_management/employee_removal.ftl />" >Employee removal</a>
        <a href="<@spring.url value="../admin/employee_management/employee_manager.ftl />" >Employee manager</a>
        <a href="<@spring.url value="../admin/employee_management/serv_assignment.ftl />" >Server assignment</a>
    [/@security.authorize]
[#else]
    <a href="<@spring.url value="../employee/authorization/pass_recovery.ftl />" >Password recovery</a>
[/#if]
