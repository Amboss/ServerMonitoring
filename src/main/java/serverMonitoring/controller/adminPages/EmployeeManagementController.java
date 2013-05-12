package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import serverMonitoring.controller.common.AbstractController;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 * Handles and retrieves the ROLE_ADMIN employee_management pages depending on the URI template.
 * A admin must be log-in first he can access these pages. However, only admin can see
 * these pages.
 */
@Controller
@RequestMapping("/employee_management")
public class EmployeeManagementController extends AbstractController {

    protected static Logger logger = Logger.getLogger("EmployeeManagementController");
    private String dir = "admin/employee_management/";

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/employee_registr", method = RequestMethod.GET)
    public String getEmployeeRegistrationPage() {
        showRequestLog("employee_registr");
        return dir + "employee_registr";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/employee_update", method = RequestMethod.GET)
    public String getEmployeeUpdatePage() {
        showRequestLog("employee_update");
        return dir + "employee_update";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_removal.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/employee_removal", method = RequestMethod.GET)
    public String getEmployeeRemovalPage() {
        showRequestLog("employee_removal");
        return dir + "employee_removal";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_manager.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/employee_manager", method = RequestMethod.GET)
    public String getEmployeeManagerPage() {
        showRequestLog("employee_manager");
        return dir + "employee_manager";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/serv_assignment.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/serv_assignment", method = RequestMethod.GET)
    public String getServerAssignmentPage() {
        showRequestLog("serv_assignment");
        return dir + "serv_assignment";
    }

    /*
    * logger
    */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}
