package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ChangePasswordObject;
import serverMonitoring.model.EmployeeEntity;

/**
 * Validator for change password page
 *  - excluding current password if userName is "admin"
 */
@Component
public class PasswordValidator implements Validator {

    protected static Logger userAccessLogger = Logger.getLogger(PasswordValidator.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private EmployeeService employeeService;
    private EmployeeEntity entity;

    @Autowired
    public PasswordValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return ChangePasswordObject.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ChangePasswordObject passwordObject = (ChangePasswordObject) target;
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        /**
         *  check for empty fields, while excluding "admin" entity.
         */
        if (!login.equals("admin")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                    "required.currentPassword", "Field name is required.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword",
                "required.newPassword", "Field name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        /**
         *  check if current password is correct, while excluding "admin" entity.
         *  04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb
         *  04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb
         */
        if (!login.equals("admin")) {
            entity = employeeService.getEmployeeByLogin(login);
            assert entity != null;
            String pass2 = passwordEncoder.encodePassword(passwordObject.getCurrentPassword(), null);

            // check if DB pass equals entered old pass
            if (!entity.getPassword().equals(passwordEncoder.encodePassword(passwordObject.getCurrentPassword(), null))){
                userAccessLogger.error("wrong.currentPassword \n" + entity.getPassword()
                + "\n\t" + pass2);
                errors.rejectValue("currentPassword", "wrong.currentPassword");
            }
        }

        /**
         *  check if confirm password is correct
         */
        if (!passwordObject.getNewPassword().equals(passwordObject.getConfirmPassword())) {
            userAccessLogger.error("wrong.password");
            errors.rejectValue("confirmPassword", "wrong.password");
        }
    }
}
