package serverMonitoring.logic.service.authentication;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin handler redirect a user with temporary password to the password change page
 * instead of the originally requested page.
 */
@Transactional
public class AdminHandler implements AuthenticationSuccessHandler {

    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();
    protected static Logger userAccessLogger = Logger.getLogger(AdminHandler.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                        Authentication auth) {
        try {
            if (auth!= null && auth.isAuthenticated()) {
                if (auth.getName().equals("admin") & auth.getCredentials().equals("admin")) {
                    EmployeeEntity employeeEntity = employeeDao.findByLogin("admin");
                    if (employeeEntity.getPassword().equals(passwordEncoder.encodePassword("admin", null))) {

                        // redirecting entity to change password
                        userAccessLogger.debug("Admin is logged in first time.");
                        response.sendRedirect("/employee/password_update");
                    } else {
                        target.onAuthenticationSuccess(request, response, auth);
                    }
                }
            }
        } catch (IOException | ServletException e) {
            throw new BadCredentialsException("Error in redirection for admin!");
        }
    }

    public void proceed(HttpServletRequest request,
                        HttpServletResponse response, Authentication auth) {
        try {
            target.onAuthenticationSuccess(request, response, auth);
        } catch (IOException | ServletException e) {
            throw new BadCredentialsException("Error after redirection for admin!");
        }
    }
}
