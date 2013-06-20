package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves password_recovery, the login or denied page
 * depending on the URI template with IS_AUTHENTICATED_ANONYMOUSLY access
 *
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController{

    protected static Logger logger = Logger.getLogger(LoginLogoutController.class);

    /**
     * Handles and retrieves /WEB-INF/ftl/index.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(Model model) {
        logger.info("/index.ftl ");
        model.addAttribute("MsTime", System.currentTimeMillis());
        return new ModelAndView("index");
    }

    /**
     * Handles a logout request
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutSuccess(Model model) {
        logger.debug("Received request to logout & show index page");
        model.addAttribute("Logout Success!");
        return new ModelAndView("login?signout");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/common/message/access_denied.ftl
     * shown whenever a access denied.
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        logger.debug("Received request to show denied page");
        return new ModelAndView("common/message/access_denied");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/authorization/password_recovery.ftl
     *
     * @return the name of the ftl page
     */
    @RequestMapping(value = "/password_recovery", method = RequestMethod.GET)
    public ModelAndView getPasswordRecoveryPage() {
        logger.debug("Received request to show password_recovery page");
        return new ModelAndView("authorization/password_recovery");
    }
}