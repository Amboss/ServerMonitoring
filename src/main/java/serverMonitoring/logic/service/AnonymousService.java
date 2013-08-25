package serverMonitoring.logic.service;

import org.springframework.security.access.annotation.Secured;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;

/**
 * Handel's functionality for access with Anonymous role
 */
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public interface AnonymousService {

    /**
     * Retrieves Employee entity by E-mail
     */
    public EmployeeEntity getEmployeeByEmail(String email);

    /**
     * updating Employee password
     *
     */
    public void updateEmployeePassword(EmployeeEntity entity, String newPass);

    /**
     * Retrieves Settings
     */
    public SystemSettingsModel getSettingsByName(String name);
}
