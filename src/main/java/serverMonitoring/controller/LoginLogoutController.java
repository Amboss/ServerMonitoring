package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import serverMonitoring.controller.common.AbstractController;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController extends AbstractController {

    protected static Logger logger = Logger.getLogger("LoginLogoutController");

    @RequestMapping("/")
    public String root() {
        return index();
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/index.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping("/index.html")
    public String index() {

        logger.info("/index.htm ");
        return "index";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/login.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        logger.debug("Received request to show login page");
        return "employee/authorization/login";
    }

    /**
     * Handles and retrieves a /WEB-INF/ftl/login.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutSuccess() {
        logger.debug("Received request to logout & show index page");
        String message = "Logout Success!";
        return "index.html?loggedout" + message;
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/denied.ftl
     * shown whenever a access denied.
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        logger.debug("Received request to show denied page");
        return "common/message/access_denied";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/login.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/password_recovery", method = RequestMethod.GET)
    public String getPasswordRecoveryPage() {
        logger.debug("Received request to show password_recovery page");
        return "employee/authorization/password_recovery";
    }
}