package serverMonitoring.logic.service;

import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

/**
 *  Interface for functionality of user with ROLE_USER access
 */
public interface EmployeeService {

    /*
     * retrieve Employee by login
     */
    public EmployeeEntity getEmployeeByLogin(EmployeeEntity entity);

    /*
     * change password
     */
    public void changePassword(EmployeeEntity entity_id, String newPass);

    /*
     * retrieve server status
     */
    public ServerState getServerState(ServerEntity entity);

    /*
     * retrieve server details
     */
    public String getServerDetails(ServerEntity entity);
}
