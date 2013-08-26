package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.util.common.CustomUtils;

import java.util.List;

/**
 * Validator for Employee Update page
 */
@Component
public class EmployeeUpdateValidatior implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(EmployeeRegistrationValidator.class);

    private final String RU_ENG_PATTERN = "([a-zA-Z\\x{0430}-\\x{044F}\\x{0410}-\\x{042F} .-]+)*";

    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}){0,64}$";

    private EmployeeService employeeService;

    private AdminService adminService;

    private EmployeeEntity entity;

    private CustomUtils util;

    @Autowired
    public void setUtil(CustomUtils util) {
        this.util = util;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
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
            if (!util.getPatternMatch(entity.getEmployee_name(), RU_ENG_PATTERN)) {
                registrValidatorLogger.debug("employee_name.content");
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
            List<EmployeeEntity> list = adminService.getAllEmployee();
            try {
                for(EmployeeEntity foo: list) {
                    if(foo.getEmail().equals(entity.getEmail())) {
                        if(!foo.getId().equals(entity.getId())) {
                            errors.rejectValue("email", "email.isTaken");
                        }
                    }
                }
            } catch (RuntimeException ignore) {
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
            if (!util.getPatternMatch(entity.getEmail(), EMAIL_PATTERN)) {
                registrValidatorLogger.debug("email.content");
                errors.rejectValue("email", "email.content");
            }
        }
    }
}

