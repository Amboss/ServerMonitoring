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
     * Retrieves EmployeeEntity entity by E-mail
     */
    public EmployeeEntity getEmployeeByEmail(String email);

    /**
     * retrieve Employee by login
     */
    public EmployeeEntity getEmployeeByLogin(String entityLogin) ;

    /**
     * retrieve Employee by Id
     */
    public EmployeeEntity getEmployeeById(Long entityId);

    /**
     * update Employee Password
     */
    public void updateEmployeePassword(EmployeeEntity entityId, String newPass);

    /**
     * updating Employee Last Login timestamp
     */
    public void changeLastLogin(String userName);

    /**
     * updating Employee Last Login timestamp
     */
    public void changeServerLastCheck(String serverName);

    /**
     * retrieve server details by provided Id
     */
    public List<ServerEntity> getServerListById(Long id);

    /**
     * retrieve server details by provided server name
     */
    public ServerEntity getServerByName(String serverName);


}
