package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract controller for common pages functionality.
 * - Creating userName var
 */
@Controller
public abstract class CustomAbstractController {

    protected static Logger logger = Logger.getLogger(CustomAbstractController.class);
    private String userName;

    public String getUserName() {
        return userName;
    }

    /**
     * @return UserName var to be shown in header after authorisation on every page
     */
    @ModelAttribute("username")
    public String setUsername() {
        userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (getUserName().equals("anonymousUser")) {
            return null;
        } else {
            return getUserName();
        }
    }

    /**
     * customised logger
     */
    public void showRequestLog(String str) {
        logger.debug("Received request to show " + str + " page");
    }

    /**
     * customised redirect
     */
    public void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new BadCredentialsException("Error!");
        }
    }
}
