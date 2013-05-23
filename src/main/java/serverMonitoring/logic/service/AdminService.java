package serverMonitoring.logic.service;

import org.springframework.security.access.annotation.Secured;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

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
    public void deleteServer(Long entity_id);

    /**
     * retrieve all Servers
     */
    public List<ServerEntity> getAllServers();

    /**
     * retrieve all Employees
     */
    public List<EmployeeEntity> getAllEmployee();
}
