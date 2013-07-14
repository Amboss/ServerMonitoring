package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.CustomAbstractController;

/**
 * Handles and retrieves the ROLE_USER page depending on the URI template.
 * A user must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_USER")
public class EmployeeController extends CustomAbstractController {

    protected static Logger logger = Logger.getLogger(EmployeeController.class);
    private String catalogPath = "employee/";

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring.ftl
     * - Changing lastLogin row
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee/monitoring", method = RequestMethod.GET)
    public ModelAndView getMonitoringPage() {
        showRequestLog("monitoring");

        //Changing lastLogin row in authorized user
       // employeeService.changeLastLogin(getUserName());

        return new ModelAndView(catalogPath + "monitoring");
    }


    /**
     * Handles and retrieves /WEB-INF/ftl/employee/serv_details.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee/serv_details", method = RequestMethod.GET)
    public ModelAndView getServerDetailsPage() {
        showRequestLog("serv_details");
        return new ModelAndView(catalogPath + "serv_details");
    }
}