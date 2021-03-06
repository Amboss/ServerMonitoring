package serverMonitoring.controller.adminPages.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;

/**
 * Handles and retrieves the ROLE_ADMIN server_management pages depending on the URI template.
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/server_management")
public class ServerManagementController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(ServerManagementController.class);

    @Autowired
    private AdminService adminService;

    /**
     * Retrieves /WEB-INF/ftl/admin/server_management/serv_manager.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/serv_manager", method = RequestMethod.GET)
    public ModelAndView getServerManagerPage() {
        showRequestLog("serv_manager");

        return new ModelAndView("admin/server_management/serv_manager", "server",
                adminService.getAllServers() );
    }
}

