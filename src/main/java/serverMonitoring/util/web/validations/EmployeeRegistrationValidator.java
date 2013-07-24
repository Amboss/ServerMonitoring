package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;

/**
 * Validator for redistration of new employee page
 */
@Component
public class EmployeeRegistrationValidator implements Validator {

    protected static Logger passwordValidatorLogger = Logger.getLogger(EmployeeRegistrationValidator.class);

    private AdminService adminService;

    private EmployeeEntity entity;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
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

        entity = (EmployeeEntity) target;


    }
}