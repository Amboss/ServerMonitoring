package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the ROLE_ADMIN server_management pages depending on the URI template.
 * A admin must be log-in first he can access these pages. However, only admin can see
 * these pages.
 */
@Controller
@RequestMapping("/server_management")
public class ServerManagementController {

    protected static Logger logger = Logger.getLogger("ServerManagementController");
    private String dir = "admin/server_management/";

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/server_management/serv_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/serv_registr", method = RequestMethod.GET)
    public String getServerRegistrationPage() {
        showRequestLog("serv_registr");
        return dir + "serv_registr";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/server_management/serv_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/serv_update", method = RequestMethod.GET)
    public String getServerUpdatePage() {
        showRequestLog("serv_update");
        return dir + "serv_update";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/server_management/serv_removal.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/serv_removal", method = RequestMethod.GET)
    public String getServerRemovalPage() {
        showRequestLog("serv_removal");
        return dir + "serv_removal";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/server_management/serv_manager.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/serv_manager", method = RequestMethod.GET)
    public String getServerManagerPage() {
        showRequestLog("serv_manager");
        return dir + "serv_manager";
    }

    /*
     * logger
     */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}

