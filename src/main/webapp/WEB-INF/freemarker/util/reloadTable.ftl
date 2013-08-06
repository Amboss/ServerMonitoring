<#-- =====================================================================
     Reload of table
        - if included, "reloadTime" variable must be defined
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<script type="text/javascript">
    var jQreloadTime = ${reloadTime?c};

    function ReloadPage() {
        location.reload();
    };

    $('#monitoringTable').ready(function() {
        setTimeout("ReloadPage()", jQreloadTime);
    });
</script>