package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.PasswordRecoveryObject;

/**
 * Validator for password recovery page functionality
 * - generating random string
 * - check for existing email
 */
@Component
public class PasswordRecoveryValidator implements Validator {
    protected static Logger userAccessLogger = Logger.getLogger(PasswordRecoveryValidator.class);
    private AnonymousService anonymousService;
    private EmployeeEntity employeeEntity;

    @Autowired
    public void setAnonymousService(AnonymousService anonymousService) {
        this.anonymousService = anonymousService;
    }

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return PasswordRecoveryObject.class.isAssignableFrom(clazz);
    }

    /**
     * @param target the object that is to be validated (can be {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     */
    @Override
    public void validate(Object target, Errors errors) {

        PasswordRecoveryObject passwordRecoveryObject = (PasswordRecoveryObject) target;

        /**
         *  check if any employee exists with provided E-mail
         */
        try {
            employeeEntity = anonymousService.getEmployeeByEmail(passwordRecoveryObject.getEmail());
        } catch (RuntimeException e) {
            userAccessLogger.error("email.rejected");
            errors.rejectValue("email", "email.rejected");
        }

        /**
         *  check if employee Active state is not expired
         */
        if (employeeEntity != null && employeeEntity.getActive().equals(0)) {
            userAccessLogger.error("email.access_denied");
            errors.rejectValue("email", "email.access_denied");
        }
    }
}
