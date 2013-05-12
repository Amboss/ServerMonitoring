package serverMonitoring.logic.service;

import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
public interface EmployeeService {

    /*
     * change password
     */
    public EmployeeEntity changePassword(EmployeeEntity entity);

    /*
     * retrieve server status
     */
    public ServerState getServerState(ServerEntity entity);

    /*
     * retrieve server details
     */
    public String getDetails(ServerEntity entity);
}
