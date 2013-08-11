package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.ftl.SystemSettingsModel;

/**
 * Validator for Settings Update page
 */
@Component
public class SettingsUpdateValidator implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(SettingsUpdateValidator.class);

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the SystemSettingsModel instances
        return SystemSettingsModel.class.isAssignableFrom(clazz);
    }

    /**
     * @param target the object that is to be validated (can be {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     */
    @Override
    public void validate(Object target, Errors errors) {

       // SystemSettingsModel model = (SystemSettingsModel) target;

        /**
         *  check for serverScanInterval
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serverScanInterval",
                "serverScanInterval.required", "Field name is required.");

        /**
         *  check if timeoutOfRespond is integer
         */



        /**
         *  check for timeoutOfRespond
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeoutOfRespond",
                "timeoutOfRespond.required", "Field name is required.");

        /**
         *  check if timeoutOfRespond is integer
         */



        /**
         *  check for pageReloadTime
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pageReloadTime",
                "pageReloadTime.required", "Field name is required.");

        /**
         *  check if pageReloadTime is integer
         */




        /**
         *  check for smtpServerAddress
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpServerAddress",
                "smtpServerAddress.required", "Field name is required.");



        /**
         *  check for smtpServerPort
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpServerPort",
                "smtpServerPort.required", "Field name is required.");

        /**
         *  check if smtpServerPort is integer
         */


    }
}
