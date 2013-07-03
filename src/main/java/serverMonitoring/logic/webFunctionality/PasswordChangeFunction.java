package serverMonitoring.logic.webFunctionality;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

/**
 * Class handel's password update page functionality
 */
@Secured("ROLE_USER")
public class PasswordChangeFunction extends UserAuthentication{

    protected static Logger userAccessLogger = Logger.getLogger(PasswordChangeFunction.class);
    private EmployeeService employeeService;
    private EmployeeEntity entity;

    @Autowired
    public void setAdminService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
     * main method
     */
    public void setPasswordChange(String username, String oldPassword,
                          String newPassword, String newPassword2) {
        entity = new EmployeeEntity();
        entity.setLogin(username);
        entity = employeeService.getEmployeeByLogin(entity);
        try {
            if (validateNewPassword(newPassword)) {
                if (newPassword.equals(newPassword2)) {
                    if (getPasswordConfirmation(oldPassword)) {
                        // updating password
                        employeeService.changePassword(entity, newPassword);
                    } else {
                        throw new BadCredentialsException("Mismatch of old password!");
                    }
                } else {
                    throw new BadCredentialsException("Mismatch of password copy!");
                }
            } else {
                throw new BadCredentialsException("Wrong chars used for password!");
            }
        } catch (BadCredentialsException e) {
            throw new RuntimeException();
        }
    }

    /*
     * check all forms
     */
    public boolean getPasswordConfirmation(String oldPassword) {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        boolean confirm = false;
            if (passwordEncoder.isPasswordValid(entity.getPassword(),
                    passwordEncoder.encodePassword(oldPassword, null), null)) {
                confirm = true;
            }
        return confirm;
    }

    /*
     * validating new password form
     */
    public boolean validateNewPassword(String newPassword) {

        // if validation is past then return true
        return true;
    }
}
