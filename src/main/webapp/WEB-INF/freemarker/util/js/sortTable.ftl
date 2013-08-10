<#-- =====================================================================
     Sorting table
        - if included, id of table must be defined
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<script type="text/javascript">
    debugger
    $.tablesorter.addParser({
        id: 'state',
        is: function(s) {
            return false;
        },

        format: function(s) {
            if ( s == '' )
            return 0;

            return parseInt(s.replace(/OK/,2).replace(/WARN/,1).replace(/FAIL/,0));
        },

        type: 'numeric'
    });

     $(function() {
           $('#monitoringTable').tablesorter({
            headers: { 2: { sorter: 'state' } }
        });
    });
</script>