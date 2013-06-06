package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the login or denied page depending on the URI template
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
        ModelAndView foo = new ModelAndView("index");
        logger.info("/index.ftl ");
        model.addAttribute("MsTime", System.currentTimeMillis());
        return foo;
    }

//
//    /**
//     * Handles a logout request
//     *
//     * @return the name of the ftl page
//     */
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutSuccess() {
//        logger.debug("Received request to logout & show index page");
//        String message = "Logout Success!";
//        return "/login?signout" + message;
//    }
//
//    /**
//     * Handles and retrieves /WEB-INF/ftl/common/message/access_denied.ftl
//     * shown whenever a access denied.
//     *
//     * @return the name of the ftl page
//     */
//    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
//    public String getDeniedPage() {
//        logger.debug("Received request to show denied page");
//        return "common/message/access_denied";
//    }
//
//    /**
//     * Handles and retrieves /WEB-INF/ftl/authorization/password_recovery.ftl
//     *
//     * @return the name of the ftl page
//     */
//    @RequestMapping(value = "/password_recovery", method = RequestMethod.GET)
//    public String getPasswordRecoveryPage() {
//        logger.debug("Received request to show password_recovery page");
//        return "authorization/password_recovery";
//    }
}