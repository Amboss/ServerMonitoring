<#-- ==============================================================
     FOOTER part of common layout
     ============================================================== -->
<#import "message/info.ftl" as my>

        <div id="modal-footer">

        <p>This is footer</p>
            <div class="footer_menu">
                <ul>
                    <li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
                </ul>
                <#include "footer/footer_menu.ftl">
            </div>
            <div class="footer_copyright">
                <#include "footer/copyright.ftl">
            </div>
        </div>
    </body>
</html>