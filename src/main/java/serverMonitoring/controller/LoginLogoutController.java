package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves password_recovery, the login or denied page.
 * depending on the URI template with IS_AUTHENTICATED_ANONYMOUSLY access.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/auth")
public class LoginLogoutController extends AbstractCommonController {

    protected static Logger logger = Logger.getLogger(LoginLogoutController.class);

    /**
     * Handles and retrieves /WEB-INF/ftl/index.ftl
     *
     * @return the name of the ftl page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        logger.info("/index.ftl ");
        return new ModelAndView("index");
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/common/message/access_denied.ftl
     * shown whenever a access denied.
     *
     * @return the name of the ftl page.
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        logger.debug("Received request to show denied page");
        return new ModelAndView("common/message/access_denied");
    }
}