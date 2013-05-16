package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import serverMonitoring.controller.common.AbstractController;

/**
 * Handles and retrieves the ROLE_ADMIN Settings page.
 * A admin must be log-in first he can access these pages.
 * However, only admin can see these page.
 */
@Controller
@RequestMapping("/settings")
public class AdminSettingsController extends AbstractController {

    protected static Logger logger = Logger.getLogger("AdminSettingsController");
    private String dir = "admin/settings/";

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/settings/change_settings.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/change_settings", method = RequestMethod.GET)
    public String getAdminSettingsPage() {
        showRequestLog("change_settings");
        return dir + "change_settings ";
    }

    /*
    * logger
    */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}
