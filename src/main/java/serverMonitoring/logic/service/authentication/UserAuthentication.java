package serverMonitoring.logic.service.authentication;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.dao.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * this class responsible for the Authorized System Access
 * and first entrance of default entity
 */

@Service("userAuthentication")
public class UserAuthentication implements AuthenticationManager {

    protected static Logger userAccessLogger = Logger.getLogger("UserAuthentication");
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Authentication authenticate(Authentication auth) throws UsernameNotFoundException {

        /**
         * Init a database user object
         */
        EmployeeEntity employeeEntity = employeeDao.findByLogin(auth.getName());
        assert employeeEntity != null;

        /**
         * Compare passwords
         * Make sure to encode the password first before comparing
         */
        if (!passwordEncoder.isPasswordValid(employeeEntity.getPassword(), (String) auth.getCredentials(), null)) {
            throw new BadCredentialsException("Wrong password!");
        }

        /**
         * Init a first entrance of default entity
         *
         * must be redirected to /employee/monitoring/password_update.ftl
         * with message PLEASE CHANGE YOUR PASSWORD
         */
        if (auth.getName().equals("admin") & auth.getCredentials().equals("admin")) {
            employeeEntity = employeeDao.findByLogin("admin");
            if (employeeEntity.getPassword().equals(passwordEncoder.encodePassword("admin", null))) {
                //  must be redirected to /employee/monitoring/password_update.ftl
                userAccessLogger.debug("Admin is logged in");
//                EmployeeController controller = new EmployeeController();
//                controller.getPasswordUpdatePage();
//                String message = "PLEASE CHANGE YOUR PASSWORD";
            }
        }

        /**
         * main logic of authentication manager
         * Username and password must be the same to authenticate
         */
        if (auth.getName().equals(auth.getCredentials())) {

            throw new BadCredentialsException("Entered login and password are the same!");
        } else {
            userAccessLogger.debug("User is located!");
            return new UsernamePasswordAuthenticationToken(
                    auth.getName(),
                    auth.getCredentials(),
                    getAuthorities(employeeEntity.getAdmin()));
        }
    }

    /**
     * Retrieves the correct ROLE type depending on the access level
     */
    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        // Create a list of grants for this user
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        // All users are granted with ROLE_USER access by default
        userAccessLogger.debug("Grant ROLE_USER to this user");
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Check if this user has admin access
        // interpret Integer(1) as an admin user
        if (access.compareTo(1) == 0) {
            // User has admin access
            userAccessLogger.debug("Grant ROLE_ADMIN to this user");
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        // Return list of granted authorities
        return authList;
    }

}
