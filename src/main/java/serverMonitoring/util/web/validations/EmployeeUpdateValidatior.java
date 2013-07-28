package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

/**
 * Validator for Employee Update page
 */
@Component
public class EmployeeUpdateValidatior implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(EmployeeRegistrationValidator.class);

    private EmployeeService employeeService;

    private EmployeeEntity entity;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the Employee instances
        return EmployeeEntity.class.isAssignableFrom(clazz);
    }

    /**
     * @param target the object that is to be validated (can be {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     */
    @Override
    public void validate(Object target, Errors errors) {

        /**
         *  check for empty Name
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employee_name",
                "employee_name.required", "Field name is required.");

        /**
         *  check for empty E-mail
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "email.required", "Field name is required.");
    }
}

