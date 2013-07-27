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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
     * Retrieves EmployeeEntity entity by E-mail
     *
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        employeeLogger.debug("retrieving Employee with e-mail: " + email);
        return employeeDao.findByEmail(email);
    }

    /**
     * retrieve Employee by login
     *
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByLogin(String entity_login) {
        employeeLogger.debug("retrieving Employee with login: " + entity_login);
        return employeeDao.findByLogin(entity_login);
    }

    /**
     * retrieve Employee by Id
     *
     * @return Employee Entity object
     */
    public EmployeeEntity getEmployeeById(Long entityId) {
        employeeLogger.debug("retrieving Employee with Id: " + entityId);
        return employeeDao.findById(entityId);
    }

    /**
     * updating Employee password
     */
    @Override
    public void updateEmployeePassword(EmployeeEntity entity, String newPass) {
        employeeLogger.debug("updating Employee Password with login: " + entity.getLogin());
        // selecting target entity

        EmployeeEntity entity2 = employeeDao.findByLogin(entity.getLogin());
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
     * updating Employee Last Login timestamp
     */
    @Override
    public void changeServerLastCheck(String serverName) {
        employeeLogger.debug("updating Server LastChek");
        ServerEntity entity = new ServerEntity();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        entity.setServer_name(serverName);
        entity.setLastCheck(timestamp);
        serverDao.update(entity);
    }

    /**
     * retrieve server details by provided Id
     *
     * @return ServerEntity object
     */
    @Override
    public List<ServerEntity> getServerListById(Long id) {
        employeeLogger.debug("retrieving Server status with id: " + id);
        return serverDao.findAllById(id);
    }

    /**
     * retrieve server details by provided server name
     *
     * @return ServerEntity object
     */
    @Override
    public ServerEntity getServerByName(String serverName) {
        employeeLogger.debug("retrieving Server details with id: " + serverName);
        return serverDao.findByServerName(serverName);
    }
}
