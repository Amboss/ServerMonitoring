package serverMonitoring.logic.service;

import org.springframework.security.access.annotation.Secured;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import java.util.List;

/**
 * Interface for functionality of user with ROLE_USER access
 */
@Secured("ROLE_USER")
public interface EmployeeService {

    /**
     * retrieve Employee by login
     */
    public EmployeeEntity getEmployeeByLogin(String entity_login) ;

    /**
     * update Employee Password
     */
    public void updateEmployeePassword(EmployeeEntity entity_id, String newPass);

    /**
     * updating Employee Last Login timestamp
     */
    public void changeLastLogin(String userName);

    /**
     * retrieve server details by provided Id
     */
    public List<ServerEntity> getServerListById(Long id);

    /**
     * retrieve server details by provided server name
     */
    public ServerEntity getServerByName(String serverName);


}
