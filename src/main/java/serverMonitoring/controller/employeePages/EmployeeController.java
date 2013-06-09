package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the ROLE_USER page depending on the URI template.
 * A user must be log-in first he can access these pages.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee")
public class EmployeeController {

    protected static Logger logger = Logger.getLogger("EmployeeController");

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public ModelAndView getMonitoringPage(Model model) {
        showRequestLog("monitoring");
        return new ModelAndView("employee/monitoring");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/password_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/password_update", method = RequestMethod.GET)
    public ModelAndView getPasswordUpdatePage() {
        showRequestLog("password_update");
        return new ModelAndView("employee/password_update");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/serv_details.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/serv_details", method = RequestMethod.GET)
    public ModelAndView getServerDetailsPage() {
        showRequestLog("serv_details");
        return new ModelAndView("employee/serv_details");
    }

    /*
    * logger
    */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}