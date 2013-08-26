package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.util.common.CustomUtils;

/**
 * Validator for registration of new employee
 */
@Component
public class EmployeeRegistrationValidator implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(EmployeeRegistrationValidator.class);

    private final String RU_ENG_PATTERN = "([a-zA-Z\\x{0430}-\\x{044F}\\x{0410}-\\x{042F} .-]+)*";

    private final String ENG_PATTERN = "^([a-zA-Z]+[_-])*";

    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}){0,64}$";

    private EmployeeService employeeService;

    private EmployeeEntity entity;

    private CustomUtils util;

    @Autowired
    public void setUtil(CustomUtils util) {
        this.util = util;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the Employee instances
        return EmployeeEntity.class.isAssignableFrom(clazz);
    }

    @Override
    @SuppressWarnings("null")
    public void validate(Object target, Errors errors) {

        entity = (EmployeeEntity) target;

        /**
         *  check for empty employee_name
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employee_name",
                "employee_name.required", "Field name is required.");

        if (entity.getEmployee_name() != null) {
            /*
             *  check for employee_name length
             */
            if (entity.getEmployee_name().length() > 128) {
                errors.rejectValue("employee_name", "employee_name.length");
            }

            /*
             *  check employee_name content
             */
            if (util.getPatternMatch(entity.getEmployee_name(), RU_ENG_PATTERN)) {
                errors.rejectValue("employee_name", "employee_name.content");
            }
        }

        /**
         *  check for empty Login
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login",
                "login.required", "Field name is required.");

        if (entity.getLogin() != null) {
            /*
             *   check for duplicated "login"
             */
            try {
                employeeService.getEmployeeByLogin(entity.getLogin());
                errors.rejectValue("login", "login.isTaken");
            } catch (RuntimeException e) {
                registrValidatorLogger.debug("Login is not occupied");
            }

             /*
             *  check for login length
             */
            if (entity.getLogin().length() > 16) {
                errors.rejectValue("login", "login.length");
            }

            /*
             *  check employee_name content
             */
            if (util.getPatternMatch(entity.getLogin(), ENG_PATTERN)) {
                errors.rejectValue("employee_name", "employee_name.content");
            }
        }

        /**
         *  check for empty E-mail
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "email.required", "Field name is required.");

        if (entity.getEmail() != null) {
            /**
             *  check for duplicated E-mail
             */
            try {
                employeeService.getEmployeeByEmail(entity.getEmail());
                errors.rejectValue("email", "email.isTaken");
            } catch (RuntimeException e) {
                registrValidatorLogger.debug("email is not occupied");
            }

            /*
             *  check for E-mail length
             */
            if (entity.getEmail().length() > 64) {
                errors.rejectValue("email", "email.length");
            }

            /*
             *  check employee_name content
             */
            if (util.getPatternMatch(entity.getEmail(), EMAIL_PATTERN)) {
                errors.rejectValue("email", "email.content");
            }
        }
    }
}