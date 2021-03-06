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
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.PasswordUpdateModel;

/**
 * Validator for change password page
 * - excluding current password if userName is "admin"
 */
@Component
public class PasswordUpdateValidator implements Validator {

    protected static Logger passwordValidatorLogger = Logger.getLogger(PasswordUpdateValidator.class);

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

    private EmployeeService employeeService;

    private EmployeeEntity entity;

    @Autowired
    public PasswordUpdateValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the Password instances
        return PasswordUpdateModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PasswordUpdateModel updateModel = (PasswordUpdateModel) target;
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        /**
         *  currentPassword excluding "admin" entity.
         */
        if (!login.equals("admin")) {

            /**
             *  check for empty currentPassword field
             */
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword",
                    "required.currentPassword", "Field name is required.");

            /**
             *  check if current password is correct, while excluding "admin" entity.
             */
            if (updateModel.getCurrentPassword() != null) {
                entity = employeeService.getEmployeeByLogin(login);

                /**
                 *  check if DB pass equals entered old pass
                 */
                if (!entity.getPassword().equals(
                        passwordEncoder.encodePassword(updateModel.getCurrentPassword(), null))) {
                    passwordValidatorLogger.error("wrong.currentPassword");
                    errors.rejectValue("currentPassword", "wrong.currentPassword");
                }
            }
        }

        /**
         *  newPassword
         *  check for empty newPassword field
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword",
                "required.newPassword", "Field name is required.");

        /**
         *  newPassword
         *  check for newPassword length
         */
        if (updateModel.getNewPassword() != null) {
            if (updateModel.getNewPassword().length() < 6 | updateModel.getNewPassword().length() > 16) {
                errors.rejectValue("newPassword", "password.length");
            }
        }

        /**
         *  confirmPassword
         *  check for empty confirmPassword field
         */
        if (updateModel.getNewPassword() != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                    "required.confirmPassword", "Field name is required.");
            /*
             *  check if confirm password is correct
             */
            if (!updateModel.getNewPassword().equals(updateModel.getConfirmPassword())) {
                passwordValidatorLogger.error("wrong.password");
                errors.rejectValue("confirmPassword", "wrong.password");
            }
        }
    }
}
