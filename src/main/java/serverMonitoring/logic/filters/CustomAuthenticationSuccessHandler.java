package serverMonitoring.logic.filters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom Authentication Success Handler
 * - Changing Last login
 * - Admin handler redirect
 */
public class CustomAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    protected static Logger userAccessLogger = Logger.getLogger(
            CustomAuthenticationSuccessHandler.class);
    private EmployeeService employeeService;
    private final String ENTITY_NAME = "admin";

    @Autowired
    public void intiEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Calls the parent class handle() method to forward or redirect to the target URL,
     * and then calls clearAuthenticationAttributes() to remove any leftover session data.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        /**
         * Changing Last login timestamp of current user
         */
        if (authentication != null && authentication.isAuthenticated()) {
            employeeService.changeLastLogin(authentication.getName());
        }

        /**
         * Admin handler redirect a Admin with temporary password to the
         * password change page instead of the originally requested page.
         */
        if (authentication != null && authentication.isAuthenticated()) {

            EmployeeEntity employeeEntity = employeeService.getEmployeeByLogin(ENTITY_NAME);
            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
            String shaPassword = passwordEncoder.encodePassword(ENTITY_NAME, null);

            if (authentication.getName().equals(ENTITY_NAME)) {
                if (authentication.getCredentials().toString().equals(ENTITY_NAME)){
                    if (employeeEntity.getPassword().equals(shaPassword)) {

                        // redirecting entity to change password
                        redirect(request, response, "/admin/admin_update_pass");
                    }
                } else {
                    // redirecting entity to change password
                    redirect(request, response, "/employee/monitoring");
                }
            }
        }
    }

    /**
     * customised redirect
     */
    private void redirect(HttpServletRequest request, HttpServletResponse response,
                          String path) throws ServletException {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new BadCredentialsException("Error while redirection of admin!");
        }
    }

    /**
     * admin - 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
     * pass -  d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1
     * user -  04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb
     */
}
