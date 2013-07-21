package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the ROLE_ADMIN Settings page.
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/settings")
public class AdminSettingsController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(AdminSettingsController.class);
    private String catalogPath = "admin/settings/";
    /**
     * Handles and retrieves /WEB-INF/ftl/admin/settings/change_settings.ftl
     *
     * @return the name of the FreeMarker template page
     */

    @RequestMapping(value = "/change_settings", method = RequestMethod.GET)
    public ModelAndView getAdminSettingsPage() {
        showRequestLog("change_settings");
        return new ModelAndView(catalogPath + "change_settings");
    }

}
