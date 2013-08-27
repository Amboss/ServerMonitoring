package serverMonitoring.util.web.validations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;
import serverMonitoring.util.common.CustomUtils;
import serverMonitoring.util.network.SimpleScanner;

import java.util.List;

/**
 * Validator for registration of new server
 */
@Controller
public class ServerRegistrationValidator implements Validator {

    protected static Logger registrValidatorLogger = Logger.getLogger(ServerRegistrationValidator.class);

    private final String NAME_PATTERN = "(?=^.{1,128}$)(^(?:(?!\\d+\\.)[a-zA-Z0-9_\\-]{1,63}\\.?)+(?:[a-zA-Z]{2,})$)";

    private final String ADDRESS_PATTERN = "(?=^.{1,128}$)(^(?:(?!\\d+\\.)[a-zA-Z0-9_\\-]{1,63}\\.?)+(?:[a-zA-Z]{2,})$)";

    private final String URL_PATTERN = "([a-z0-9]|[a-z0-9][a-z0-9\\-]{0,61}[a-z0-9])(\\.[a-z0-9]|[a-z0-9][a-z0-9\\-]{0,61}[a-z0-9])*\\.?";

    private AdminService adminService;

    private EmployeeService employeeService;

    private ServerEntity entity;

    private CustomUtils util;

    private SimpleScanner customScanner;

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

    @Autowired
    public void setCustomScanner(SimpleScanner customScanner) {
        this.customScanner = customScanner;
    }

    @Override
    public boolean supports(Class clazz) {
        // validate the Employee instances
        return ServerEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        entity = (ServerEntity) target;

        /**
         *  check for empty server_name
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "server_name",
                "server_name.required", "Field server_name is required.");


        if (entity.getServer_name() != null) {

            /*
             *   check for duplicated server_name
             */
            List<ServerEntity> dbEntity = adminService.getAllServers();
            try {
                for (ServerEntity foo : dbEntity) {
                    if (foo.getServer_name().equals(entity.getServer_name())) {
                        if (!foo.getId().equals(entity.getId())) {
                            errors.rejectValue("server_name", "server_name.isTaken");
                        }
                    }
                }
            } catch (Exception ignore) {
                registrValidatorLogger.debug("Server_name is not occupied");
            }
            /*
             *   check for server_name length max length = 128
             */
            if (entity.getServer_name().length() > 128) {
                errors.rejectValue("server_name", "server_name.length");
            }
            /*
             *   check for server_name content
             */
            if (util.getPatternMatch(entity.getServer_name(), NAME_PATTERN)) {
                errors.rejectValue("server_name", "server_name.content");
            }
        }

        /**
         *  check for empty address
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
                "address.required", "Field address is required.");

        if (entity.getAddress() != null) {
            /*
             *   check for address length max length = 128
             */
            if (entity.getAddress().length() > 128) {
                errors.rejectValue("address", "address.length");
            }
            /*
             *   check for address content
             */
            if (util.getPatternMatch(entity.getServer_name(), ADDRESS_PATTERN)) {
                errors.rejectValue("address", "address.content");
            }
        }

        /**
         *  check for empty port
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port",
                "port.required", "Field port is required.");
        /*
         *   check if port is acceptable
         */
        if (entity.getPort() != null) {
            SystemSettingsModel settings = employeeService.getSettingsByName("default");
            boolean portScan = customScanner.doSimpleScan(
                    entity.getAddress(),
                    entity.getPort(),
                    settings.getTimeoutOfRespond());
            if (!portScan) {
                errors.rejectValue("port", "port.content");
            }
        }

        /**
         *  check for empty url
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url",
                "url.required", "Field url is required.");

        if (entity.getUrl() != null) {
            /*
             *   check for URL length max length = 128
             */
            if (entity.getUrl().length() > 128) {
                errors.rejectValue("url", "url.length");
            }
            /*
             *   check for url content
             */
            if (util.getPatternMatch(entity.getUrl(), URL_PATTERN)) {
                errors.rejectValue("url", "url.content");
            }
        }
    }
}