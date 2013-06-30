package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Abstract controller for common pages functionality.
 */
@Controller
@Secured("ROLE_USER")
public abstract class CustomAbstractController {

    protected static Logger logger = Logger.getLogger(CustomAbstractController.class);
    private String userName;

    public String getUserName() {
        return userName;
    }

    /*
     * Creating UserName var to be shown in header after authorisation on every page
     */
    @ModelAttribute("username")
    public String setUsername() {

        userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (getUserName().equals("anonymousUser")) {
            return null;
        } else {
            return getUserName();
        }
//        (final HttpServletRequest request)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        //create a new session with new Attribute
//        HttpSession session = request.getSession(true);
//        session.setAttribute("username", userName);

    }
}
