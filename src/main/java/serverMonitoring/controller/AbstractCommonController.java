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
 * TODO pass: d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1
 */
@Controller
public abstract class AbstractCommonController {

    protected static Logger logger = Logger.getLogger(AbstractCommonController.class);

    private String userName;



    /**
     * @return current Entity login
     */
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
     * @return reloadTable for estimated reload time for data tables
     */
    @ModelAttribute("reloadTable")
    public Integer setReloadTable() {
       return null;
    }

    /**
     * @return true if currant user has admin role
     */
    public boolean isUserIsAdmin(HttpServletRequest request) {
        return request.isUserInRole("ROLE_ADMIN");
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
