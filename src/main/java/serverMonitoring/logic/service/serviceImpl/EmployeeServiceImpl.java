package serverMonitoring.logic.service.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
     * retrieve Employee by login
     *
     * @return Employee Entity object
     */
    @Override
    @Secured("ROLE_USER")
    public EmployeeEntity getEmployeeByLogin(EmployeeEntity entity_login) {
        EmployeeEntity entity = new EmployeeEntity();
        String str = "Employee with login: " + entity_login.getLogin();
        employeeLogger.debug("updating " + str);
        try {
            entity = employeeDao.findByLogin(entity_login.getLogin());
        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error in update of " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * updating Employee
     */
    @Override
    @Secured("ROLE_USER")
    public void changePassword(EmployeeEntity entity_id, String newPass) {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        String str = "Employee Password with id: " + entity_id.getId();
        EmployeeEntity entity = new EmployeeEntity();
        employeeLogger.debug("updating " + str);
        try {
            entity.setPassword(passwordEncoder.encodePassword(newPass, null));
            employeeDao.update(entity);
        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error in update of " + str);
            e.printStackTrace();
        }
    }

    /**
     * retrieve server status
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public ServerState getServerState(ServerEntity entity_id) {
        String str = "Server status with id: " + entity_id.getId();
        employeeLogger.debug("retrieving " + str);
        ServerEntity entityState = null;
        try {
            entityState = serverDao.findById(entity_id.getId());
        } catch (SQLException | NullPointerException e) {
            employeeLogger.error("error while retrieving " + str);
            e.printStackTrace();
        }
        return entityState.getState();
    }

    /**
     * retrieve server details
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_USER")
    public String getServerDetails(ServerEntity entity_id) {
        String str = "server details with id: " + entity_id.getId();
        employeeLogger.debug("retrieving " + str);
        ServerEntity entityDetails = null;
        try {
            entityDetails = serverDao.findById(entity_id.getId());

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
