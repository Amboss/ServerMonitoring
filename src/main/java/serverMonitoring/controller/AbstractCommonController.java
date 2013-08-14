package serverMonitoring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import serverMonitoring.logic.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract controller for common pages functionality.
 * - Creating userName var
 */
@Controller
public abstract class AbstractCommonController {

    protected static Logger logger = Logger.getLogger(AbstractCommonController.class);

    private String userName;

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * @return current Entity login
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return UserName var to be shown in header after authorisation on every page
     * attribute "username" cached in login_form.ftl
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
    @ModelAttribute("tableReloadTime")
    public Integer getTableReloadTime() {
        return employeeService.getSettingsByName("default").getPageReloadTime();
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
//    public void redirect(HttpServletRequest request,
//                         HttpServletResponse response,
//                         String path) {
//        try {
//            response.sendRedirect(request.getContextPath() + path);
//        } catch (java.io.IOException e) {
//            throw new BadCredentialsException("Redirect error!");
//        }
//    }
}
