package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import serverMonitoring.controller.common.AbstractController;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 * Handles and retrieves the ROLE_USER page depending on the URI template.
 * A user must be log-in first he can access these pages.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends AbstractController {

    protected static Logger logger = Logger.getLogger("EmployeeController");
    private String dir = "employee/monitoring/";

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring/monitoring.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public String getMonitoringPage() {
        showRequestLog("monitoring");
        return dir + "monitoring";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring/password_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/password_update", method = RequestMethod.GET)
    public String getPasswordUpdatePage() {
        showRequestLog("password_update");
        return dir + "password_update";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring/serv_details.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/serv_details", method = RequestMethod.GET)
    public String getServerDetailsPage() {
        showRequestLog("serv_details");
        return dir + "serv_details";
    }

    /*
    * logger
    */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}