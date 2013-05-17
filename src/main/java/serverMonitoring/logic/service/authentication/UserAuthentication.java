package serverMonitoring.logic.service.authentication;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.DAOImpl.EmployeeJdbcDaoSupport;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import java.sql.SQLException;
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
    protected @Value( "#{applicationProperties['startup.entity.setLogin']}" ) String entitySetLogin;
    protected @Value( "#{applicationProperties['startup.entity.setPassword']}" ) String entitySetPassword;
    protected @Value( "#{applicationProperties['encoding.strengths']}" ) Integer encodingStrengths;
    @Autowired
    private EmployeeDao employeeDao = new EmployeeJdbcDaoSupport();
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private EmployeeEntity employeeEntity = null;

    public Authentication authenticate(Authentication auth)  throws UsernameNotFoundException {

        /*
         * Init a database user object
         */
        try {
            employeeEntity = employeeDao.findByLogin(auth.getName());
        } catch (SQLException | BadCredentialsException | NullPointerException e) {
            e.printStackTrace();
        }

        /*
         * Compare passwords
         * Make sure to encode the password first before comparing
         */
        if (!passwordEncoder.isPasswordValid(employeeEntity.getPassword(), (String) auth.getCredentials(), null)) {
            throw new BadCredentialsException("Wrong password!");
        }

        /*
         * Init a first entrance of default entity
         *
         * must be redirected to /employee/monitoring/password_update.ftl
         * with message PLEASE CHANGE YOUR PASSWORD
         */
//        if (auth.getName().equals(entitySetLogin) & auth.getCredentials().equals(entitySetPassword)) {
//            List<EmployeeEntity> entitylist = employeeDao.findAllByParam("login", entitySetLogin);
//            EmployeeEntity defaultEntity = (list.isEmpty()) ? null : list.get(0);
//            if (defaultEntity != null) {
//                if (defaultEntity.getPassword().equals(passwordEncoder.encodePassword(entitySetPassword, null))) {
//                    try {
//                        //  must be redirected to /employee/monitoring/password_update.ftl
//                        EmployeeController controller = new EmployeeController();
//                        controller.getPasswordUpdatePage();
//                        //message PLEASE CHANGE YOUR PASSWORD
//                    } catch (Exception e) {
//                        throw new RuntimeException("redirection of default entity failed" + e);
//                    }
//                }
//            } else {
//                throw new BadCredentialsException(entitySetLogin + " does not exists!");
//            }
//        }

        /*
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

    /*
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
