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
 * Admin handler redirect a user with temporary password to the
 * password change page instead of the originally requested page.
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    protected static Logger userAccessLogger = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication != null && authentication.isAuthenticated()) {

            EmployeeEntity employeeEntity = employeeService.getEmployeeByLogin(ENTITY_NAME);
            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
            String shaPassword = passwordEncoder.encodePassword(ENTITY_NAME, null);

            if (authentication.getName().equals(ENTITY_NAME) &&
                    authentication.getCredentials().toString().equals(ENTITY_NAME)) {
                if (employeeEntity.getPassword().equals(shaPassword)) {

                    // redirecting entity to change password
                    redirect(request, response, "/employee/password_update");
                }
            } else {
                // redirecting entity to originally requested page
                redirect(request, response, "/employee/monitoring");
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
     * customised redirect
     */
//    public void proceed(HttpServletRequest request, HttpServletResponse response,
//                        Authentication auth) {
//        try {
//            super.onAuthenticationSuccess(request, response, auth);
//        } catch (IOException | ServletException e) {
//            throw new BadCredentialsException("Error while proceeding customFilter!");
//        }
//    }
}
