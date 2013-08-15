<#-- =====================================================================
     Reload of table
        - if included, "reloadTime" variable must be defined
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<script type="text/javascript">

    /*  reloadTime passed from freeMarker */
    var jQreloadTime = ${reloadTime?c};

    function ReloadPage() {
        location.reload();
    };

</script>