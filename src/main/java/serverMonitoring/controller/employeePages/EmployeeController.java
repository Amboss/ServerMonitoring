package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.logic.service.authentication.AdminHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Handles and retrieves the ROLE_USER page depending on the URI template.
 * A user must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee")
public class EmployeeController {

    protected static Logger logger = Logger.getLogger(EmployeeController.class);
    private String catalogPath = "employee/";
    private AdminHandler handler;

    @Autowired
    public AdminHandler getHandler() {
        return handler;
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/monitoring.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public ModelAndView getMonitoringPage(Model model, final HttpServletRequest request) {
        showRequestLog("monitoring");

        /*
         * Creating User name var to be shown in header after authorisation
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        //create a new session with new Attribute
        HttpSession session = request.getSession(true);
        session.setAttribute("username", userName);

        return new ModelAndView(catalogPath + "monitoring");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/employee/password_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/password_update", method = RequestMethod.GET)
    public ModelAndView getPasswordUpdatePage(final HttpServletRequest request,
                                              Principal principal) {
        showRequestLog("password_update");
        final String currentUser = principal.getName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", currentUser);

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