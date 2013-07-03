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
import java.util.Date;

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
        employeeLogger.debug("retrieving Employee with login: " + entity_login.getLogin());
        return employeeDao.findByLogin(entity_login.getLogin());
    }

    /**
     * updating Employee password
     */
    @Override
    public void changePassword(EmployeeEntity entity, String newPass) {
        employeeLogger.debug("updating Employee Password with id: " + entity.getId());
        // selecting target entity
        Long id = entity.getId();
        EmployeeEntity entity2 = employeeDao.findById(id);
        // hashing new password
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        // setting new password
        entity2.setPassword(passwordEncoder.encodePassword(newPass, null));
        employeeDao.update(entity2);
    }

    /**
     * updating Employee Last Login timestamp
     */
    @Override
    public void changeLastLogin(String userName) {
        employeeLogger.debug("updating Employee LastLogin");
        EmployeeEntity entity = new EmployeeEntity();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        entity.setLogin(userName);
        entity.setLastLogin(timestamp);
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
        ServerEntity serverEntity = serverDao.findById(entity_id.getId());
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
        return serverDao.findByServerName(entity.getServer_name());
    }
}
