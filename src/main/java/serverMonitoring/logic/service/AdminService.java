package serverMonitoring.logic.service;

import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
public interface AdminService {

    /*
     *  registration of new Employee
     */
    public EmployeeEntity registerEmployee(EmployeeEntity entity);

    /*
     *  update Employee Info
     */
    public EmployeeEntity updateEmployee(EmployeeEntity entity);

    /*
     *  delete Employee
     */
    public void deleteEmployee(EmployeeEntity entity);

    /*
     *  register the new Server
     */
    public ServerEntity registerServer(ServerEntity entity);

    /*
    *  update Employee Info
    */
    public ServerEntity updateServer(ServerEntity entity);

    /*
     *  delete Employee
     */
    public void deleteServer(ServerEntity entity);
}
