<#-- ==============================================================
     FOOTER
        - for common layout
     ============================================================== -->
<#import "message/info.ftl" as my>

        <div class="container">
            <hr>
            <div class="row">
                <div class="span3 col">
                    <div class="block contact-block">
                        <h3>Contact Us</h3>
                        <address>
                        <p><abbr title="Phone"><i class="icon-phone"></i></abbr>${my.phone}</p>
                        <p><abbr title="Email"><i class="icon-envelope"></i></abbr>${my.mail}</p>
                        <p><abbr title="Address"><i class="icon-home"></i></abbr>${my.address}</p>
                        </address>
                    </div>
                </div><!-- /.span3 col -->
                <div class="span5 col">
                    <div class="block">
                        <h3>About Us</h3>
                        <p>Making the web a prettier place one template at a time! We make beautiful, quality, responsive web applications!</p>
                    </div>
                </div>
                <div class="span4 col">
                    <div class="block newsletter">
                        <h3>Newsletter</h3>
                        <p>Stay up to date with our latest news and product releases by signing up to our newsletter.</p>
                        <form class="form-inline">
                            <div class="input-append">
                                <input class="input-medium" type="text" placeholder="Email" />
                                <button class="btn btn-primary" type="button">Go!</button>
                            </div>
                        </form>
                    </div>
                </div><!-- /.span4 col -->
            </div><!-- /.row -->
            <hr>
            <div class="row-fluid">
                <div id="toplink">
                    <a href="#top" class="top-link" title="Back to top">Back To Top <i class="icon-chevron-up"></i></a></div>
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