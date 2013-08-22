<#-- ==============================================================
     FOOTER
        - for common layout
     ============================================================== -->

<#import "/util/spring.ftl" as spring />

<#import "message/info.ftl" as my>

        <div class="container">
            <hr>
            <div class="row">
                <div class="span3 col">
                    <div class="block contact-block">
                        <h3><@spring.message "footer.contact.title" /></h3>
                        <address>
                            <p><abbr title="Phone"><i class="icon-phone"></i></abbr>${my.phone}</p>
                            <p><abbr title="Email"><i class="icon-envelope"></i></abbr>${my.mail}</p>
                            <p><abbr title="Address"><i class="icon-home"></i></abbr>${my.address}</p>
                        </address>
                    </div>
                </div><!-- /.span3 col -->
                <div class="span5 col">
                    <div class="block">
                        <h3><@spring.message "footer.about.title" /></h3>
                        <p><@spring.message "footer.about.text" /></p>
                    </div>
                </div>
                <div class="span4 col">
                    <div class="block newsletter">
                        <h3><@spring.message "footer.news.title" /></h3>
                        <p><@spring.message "footer.news.text" /></p>
                    </div>
                </div><!-- /.span4 col -->
            </div><!-- /.row -->
            <hr>
            <div class="row-fluid">
                <div id="toplink">
                    <a href="#top" class="top-link" title="Back to top"><@spring.message "footer.link" /><i class="icon-chevron-up"></i></a></div>
                    <div class="subfooter">
                        <div class="span6">
                            <#include "footer/copyright.ftl">
                        </div>
                        <div class="span6">
                            <#include "footer/footer_menu.ftl">
                        </div>
                    </div>
                </div><!-- /.toplink -->
            </div><!-- /.row-fluid -->
        </div><!-- /.container -->
    </body>
</html>