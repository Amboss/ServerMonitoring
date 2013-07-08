package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.CustomAbstractController;
import serverMonitoring.logic.filters.CustomAuthenticationSuccessHandler;
import serverMonitoring.logic.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles and retrieves the ROLE_USER page depending on the URI template.
 * A user must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee")
public class EmployeeController extends CustomAbstractController {

    protected static Logger logger = Logger.getLogger(EmployeeController.class);
    private String catalogPath = "employee/";
    private CustomAuthenticationSuccessHandler handler;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public CustomAuthenticationSuccessHandler getHandler() {
        return handler;
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring.ftl
     * - Changing lastLogin row
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public ModelAndView getMonitoringPage() {
        showRequestLog("monitoring");

        //Changing lastLogin row in authorized user
        employeeService.changeLastLogin(getUserName());

        return new ModelAndView(catalogPath + "monitoring");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/password_update.ftl
     *
     * @return the name of the FreeMarker template page
     */

    @RequestMapping(value = "/password_update", method = RequestMethod.GET)
    public ModelAndView getPasswordUpdatePage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestParam(value = "new_password") String newPassword) {
        showRequestLog("password_update");



        return new ModelAndView(catalogPath + "password_update");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/serv_details.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/serv_details", method = RequestMethod.GET)
    public ModelAndView getServerDetailsPage() {
        showRequestLog("serv_details");
        return new ModelAndView(catalogPath + "serv_details");
    }

    /*
    * logger
    */
    protected void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }
}