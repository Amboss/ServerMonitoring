package serverMonitoring.logic.service.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    protected static Logger employeeLogger = Logger.getLogger("EmployeeServiceImpl");
    private EmployeeDao employeeDao;
    private ServerDao serverDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    /**
     * updating Server
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public EmployeeEntity changePassword(EmployeeEntity entity) {
        String str = "Employee Password with id: " + entity.getId();
        employeeLogger.debug("updating " + str);
        try {
            employeeDao.update(entity);
        } catch (NullPointerException e) {
            employeeLogger.error("error in update of " + str);
        }
        return entity;
    }

    /**
     * retrieve server status
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public ServerState getServerState(ServerEntity entity) {
        String str = "Server status with id: " + entity.getId();
        employeeLogger.debug("retrieving " + str);
        try {
            List<ServerEntity> list = serverDao.findAllByParam("id", entity);
            entity = (list.isEmpty()) ? null : list.get(0);
        } catch (NullPointerException e) {
            employeeLogger.error("error while retrieving " + str);
        }
        return entity.getState();
    }

    /**
     * retrieve server details
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public String getDetails(ServerEntity entity) {
        String str = "server details with id: " + entity.getId();
        employeeLogger.debug("retrieving " + str);
        try {
            List<ServerEntity> list = serverDao.findAllByParam("id", entity);
            entity = (list.isEmpty()) ? null : list.get(0);
         } catch (NullPointerException e) {
            employeeLogger.error("error while retrieving " + str);
        }
        return entity.toString();
    }
}
