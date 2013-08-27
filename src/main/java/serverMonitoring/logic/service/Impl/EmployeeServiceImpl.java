package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.DAO.SettingsDao;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;

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

    private SettingsDao settingsDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @Autowired
    public void setSettingsDao(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    /**
     * Retrieves EmployeeEntity entity by E-mail
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        employeeLogger.debug("retrieving Employee with e-mail: " + email);
        return employeeDao.findByEmail(email);
    }

    /**
     * retrieve Employee by login
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByLogin(String entity_login) {
        employeeLogger.debug("retrieving Employee with login: " + entity_login);
        return employeeDao.findByLogin(entity_login);
    }

    /**
     * retrieve Employee by Id
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
     * TODO remove !!!
     */
    @Override
    public void changeServerLastCheck(String serverName) {
        employeeLogger.debug("updating Server LastChek");

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        ServerEntity entity = serverDao.findByServerName(serverName);
        entity.setLastCheck(timestamp);
        serverDao.updateServer(entity);
    }

    /**
     * retrieve server details by provided responsible_id
     * @return ServerEntity object
     */
    @Override
    public List<ServerEntity> findAllByResponsibleId(Long responsible_id) {
        employeeLogger.debug("retrieving Server status with id: " + responsible_id);
        return serverDao.findAllByResponsibleId(responsible_id);
    }

    /**
     * retrieve server details by provided server name
     * @return ServerEntity object
     */
    @Override
    public ServerEntity getServerByName(String serverName) {
        employeeLogger.debug("retrieving Server details with id: " + serverName);
        return serverDao.findByServerName(serverName);
    }
    /**
     * retrieve server details by provided server id
     @return ServerEntity object
     */
    @Override
    public ServerEntity getServerById(Long id) {
        employeeLogger.debug("retrieving Server details with id: " + id);
        return serverDao.findServerById(id);
    }

    /**
     * Retrieves Settings
     * @return System Settings Model object
     */
    @Override
    public SystemSettingsModel getSettingsByName(String name) {
        employeeLogger.debug("Retrieving settings");
        return settingsDao.getSettingsByName(name);
    }
}
