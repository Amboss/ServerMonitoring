package serverMonitoring.logic.service.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.DAOImpl.EmployeeJdbcDaoSupport;
import serverMonitoring.logic.DAO.DAOImpl.ServerJdbcDaoSupport;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * This class responsible for functionality of user with ROLE_USER access
 */
@Service("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    protected static Logger employeeLogger = Logger.getLogger("EmployeeServiceImpl");
    private EmployeeDao employeeDao = new EmployeeJdbcDaoSupport();
    private ServerDao serverDao = new ServerJdbcDaoSupport();

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
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public EmployeeEntity changePassword(EmployeeEntity entity) {
        String str = "Employee Password with id: " + entity.getId();
        employeeLogger.debug("updating " + str);
        try {
            employeeDao.update(entity);
        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error in update of " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * retrieve server status
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public ServerState getServerState(ServerEntity entity) {
        String str = "Server status with id: " + entity.getId();
        employeeLogger.debug("retrieving " + str);
        ServerEntity entityState = null;
        try {
            entityState = serverDao.findById(entity.getId());
        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error while retrieving " + str);
            e.printStackTrace();
        }
        return entityState.getState();
    }

    /**
     * retrieve server details
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public String getDetails(ServerEntity entity) {
        String str = "server details with id: " + entity.getId();
        employeeLogger.debug("retrieving " + str);
        ServerEntity entityDetails = null;
        try {
            entityDetails = serverDao.findById(entity.getId());

        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error while retrieving " + str);
            e.printStackTrace();
        }
        return entityDetails.toString();
    }

    private static Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }
}
