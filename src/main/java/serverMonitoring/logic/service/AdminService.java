package serverMonitoring.logic.service;

import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import java.util.List;

/**
 * Interface for functionality of user with ROLE_ADMIN access
 */
public interface AdminService extends EmployeeService{

    /**
     * registration of new Employee
     */
    public EmployeeEntity registerEmployee(EmployeeEntity entity);

    /**
     * update Employee Info
     */
    public EmployeeEntity updateEmployee(EmployeeEntity entity);

    /**
     * delete Employee
     */
    public void deleteEmployee(EmployeeEntity entity);

    /**
     * register the new Server
     */
    public ServerEntity registerServer(ServerEntity entity);

    /**
     * update Employee Info
     */
    public ServerEntity updateServer(ServerEntity entity);

    /**
     * delete Server
     */
    public void deleteServer(ServerEntity entity);

    /**
     * retrieve all Servers
     */
    public List<ServerEntity> getAllServers();

    /**
     * retrieve all Employees
     */
    public List<EmployeeEntity> getAllEmployee();
}
