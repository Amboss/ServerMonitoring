package serverMonitoring.logic.service;

import org.springframework.security.access.annotation.Secured;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;

import java.util.List;

/**
 * Interface for functionality of user with ROLE_ADMIN access
 */
@Secured("ROLE_ADMIN")
public interface AdminService {

    /**
     * registration of new Employee
     */
    public void registerEmployee(EmployeeEntity entity);

    /**
     * update Employee Info
     */
    public void updateEmployee(EmployeeEntity entity);

    /**
     * delete Employee
     */
    public void deleteEmployee(Long entity_id);

    /**
     * register the new Server
     */
    public void registerServer(ServerEntity entity);

    /**
     * update Employee Info
     */
    public void updateServer(ServerEntity entity);

    /**
     * delete Server
     */
    public void deleteServer(Long id);

    /**
     * retrieve all Servers
     */
    public List<ServerEntity> getAllServers();

    /**
     * retrieve all Employees
     */
    public List<EmployeeEntity> getAllEmployee();

    /**
     * Updating existing Settings
     */
    public void updateSettings(SystemSettingsModel model);

    /**
     * Adds new Settings with new Id assignment
     */
    public void addSettings(SystemSettingsModel model);

    /**
     * Deleting Settings entity
     */
    public void deleteSettings(String settings_name);
}
