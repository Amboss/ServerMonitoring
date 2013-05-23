package serverMonitoring.logic.service;

import org.springframework.security.access.annotation.Secured;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

/**
 *  Interface for functionality of user with ROLE_USER access
 */
@Secured("ROLE_USER")
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
    public ServerEntity getServerDetails(ServerEntity entity);
}
