package serverMonitoring.logic.webFunctionality;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin handler redirect a user with temporary password to the password change page
 * instead of the originally requested page.
 */
@Transactional
//@Service("FirstEntranceOfAdminFilter")
public class FirstEntranceOfAdminFilter implements Filter {

    protected static Logger userAccessLogger = Logger.getLogger(FirstEntranceOfAdminFilter.class);
    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();
    private final String entityName = "admin";
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeDao(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // NOT WORKING !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setLogin(entityName);
        employeeEntity = employeeService.getEmployeeByLogin(employeeEntity);
        if (authentication != null && authentication.isAuthenticated()) {

                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
                String shaPassword = passwordEncoder.encodePassword(entityName, null);

                if (authentication.getName().equals(entityName) & authentication.getCredentials()
                                                                            .toString().equals(shaPassword)) {
                    if (employeeEntity.getPassword().equals(shaPassword)) {
                        // redirecting entity to change password
                        userAccessLogger.debug("Admin is logged in first time.");
                        redirect(httpServletRequest, httpServletResponse, "employee/password_update");
                    } else {
                        proceed(httpServletRequest, httpServletResponse, authentication);
                    }
                } else {
                    proceed(httpServletRequest, httpServletResponse, authentication);
                }
        } else {
            proceed(httpServletRequest, httpServletResponse, authentication);
        }
    }

    private void redirect(HttpServletRequest request,
                          HttpServletResponse response,
                          String path) throws ServletException {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new ServletException(e);
        }
    }

    public void proceed(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication auth) {
        try {
            target.onAuthenticationSuccess(request, response, auth);
        } catch (IOException | ServletException e) {
            throw new BadCredentialsException("Error after redirection of admin!");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
}
