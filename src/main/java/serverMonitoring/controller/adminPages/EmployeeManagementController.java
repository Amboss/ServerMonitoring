package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the ROLE_ADMIN employee_management pages depending on the URI template.
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management")
public class EmployeeManagementController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeManagementController.class);
    private String catalogPath = "admin/employee_management/";

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_registr", method = RequestMethod.GET)
    public ModelAndView getEmployeeRegistrationPage(Model model) {
        showRequestLog("employee_registr");
        return new ModelAndView(catalogPath + "employee_registr");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_update", method = RequestMethod.GET)
    public ModelAndView getEmployeeUpdatePage() {
        showRequestLog("employee_update");
        return new ModelAndView(catalogPath + "employee_update");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_removal.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_removal", method = RequestMethod.GET)
    public ModelAndView getEmployeeRemovalPage() {
        showRequestLog("employee_removal");
        return new ModelAndView(catalogPath + "employee_removal");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_manager.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_manager", method = RequestMethod.GET)
    public ModelAndView getEmployeeManagerPage() {
        showRequestLog("employee_manager");
        return new ModelAndView(catalogPath + "employee_manager");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/serv_assignment.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/serv_assignment", method = RequestMethod.GET)
    public ModelAndView getServerAssignmentPage() {
        showRequestLog("serv_assignment");
        return new ModelAndView(catalogPath + "serv_assignment");
    }

}
