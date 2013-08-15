<#-- =====================================================================
                                Sorting Server tables
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<script type="text/javascript">

    jQuery.extend(jQuery.fn.dataTableExt.oSort, {
        "status-enum-pre": function ( a ) {
            switch(a) {
                case "FAIL": return 1;
                case "WARN": return 2;
                case "OK": return 3;
                default: return 4;
            }
        },

        "status-enum-asc": function ( a, b ) {
            return ((a < b) ? -1 : ((a > b) ? 1 : 0));
        },

        "status-enum-desc": function ( a, b ) {
            return ((a < b) ? 1 : ((a > b) ? -1 : 0));
        },
      });

    $(document).ready(function() {
        jQuery("table").dataTable( {
            "iDisplayLength": -1,
            "aaSorting": [[ 1, "asc" ]],
            "aoColumns": [
                null,
                { "sType": "status-enum" },
                null,
                null
            ],
        });
    });
</script>