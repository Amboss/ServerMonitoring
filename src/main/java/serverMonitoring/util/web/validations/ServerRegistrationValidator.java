package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ServerEntity;

/**
 * Validator for registration of new server
 */
@Controller
public class ServerRegistrationValidator implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(ServerRegistrationValidator.class);

    private EmployeeService employeeService;

    private ServerEntity entity;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the Employee instances
        return ServerEntity.class.isAssignableFrom(clazz);
    }

    /**
     * @param target the object that is to be validated (can be {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     */
    @Override
    public void validate(Object target, Errors errors) {

        entity = (ServerEntity) target;

        /**
         *  check for empty server_name
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "server_name",
                "server_name.required", "Field server_name is required.");

        /**
         *   check for duplicated server_name
         */
        try {
            employeeService.getServerByName(entity.getServer_name());
            errors.rejectValue("server_name", "server_name.isTaken");
        } catch (RuntimeException e) {
            registrValidatorLogger.debug("Server_name is not occupied");
        }

        /**
         *  check for empty address
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
                "address.required", "Field address is required.");

        /**
         *  check for empty port
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port",
                "port.required", "Field port is required.");

        /**
         *  check for empty url
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url",
                "url.required", "Field url is required.");
    }
}