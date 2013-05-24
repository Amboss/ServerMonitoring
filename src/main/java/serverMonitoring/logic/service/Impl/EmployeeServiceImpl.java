package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;

/**
 * This class responsible for functionality of user with ROLE_USER access
 */
@Service("EmployeeServiceImpl")
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
     * retrieve Employee by login
     *
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByLogin(EmployeeEntity entity_login) {
        EmployeeEntity entity = new EmployeeEntity();
        employeeLogger.debug("updating Employee with login: " + entity_login.getLogin());
        return employeeDao.findByLogin(entity_login.getLogin());
    }

    /**
     * updating Employee
     */
    @Override
    public void changePassword(EmployeeEntity entity_id, String newPass) {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        EmployeeEntity entity = new EmployeeEntity();
        employeeLogger.debug("updating Employee Password with id: " + entity_id.getId());
        entity.setPassword(passwordEncoder.encodePassword(newPass, null));
        employeeDao.update(entity);
    }

    /**
     * retrieve server status
     *
     * @return ServerEntity object
     */

    @Override
    public ServerState getServerState(ServerEntity entity_id) {
        employeeLogger.debug("retrieving Server status with id: " + entity_id.getId());
        ServerEntity serverEntity = new ServerEntity();
        serverEntity = serverDao.findById(entity_id.getId());
        return serverEntity.getState();
    }

    /**
     * retrieve server details
     *
     * @return ServerEntity object
     */
    @Override
    public ServerEntity getServerDetails(ServerEntity entity) {
        employeeLogger.debug("retrieving Server details with id: " + entity.getId());
        return serverDao.findById(entity.getId());
    }

    /*
     * getting current date & time for SQL
     */
    private static Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }
}
