package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.DAO.SettingsDao;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;

import java.util.List;

/**
 * This class responsible for functionality of user with ROLE_ADMIN access
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    protected static Logger adminLogger = Logger.getLogger("AdminServiceImpl");

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
     * registration of new Employee
     */
    @Override
    public void registerEmployee(EmployeeEntity entity) {
        adminLogger.debug("registration of new Employee");
        employeeDao.add(entity);
    }

    /**
     * updating Employee
     */
    @Override
    public synchronized void updateEmployee(EmployeeEntity entity) {
        adminLogger.debug("updating Employee with id: " + entity.getId());
        employeeDao.update(entity);
    }

    /**
     * Retrieving list of all employees
     *
     * @return List of EmployeeEntity objects
     */
    @Override
    public List<EmployeeEntity> getAllEmployee() {
        adminLogger.debug("retrieving list of servers");
        return employeeDao.findAll();
    }

    /**
     * deleting Employee
     */
    @Override
    public synchronized void deleteEmployee(Long entity_id) {
        adminLogger.debug("deleting Employee with id: " + Long.toString(entity_id));
        employeeDao.delete(entity_id);
    }

    /**
     * registration of new Server
     */
    @Override
    public void registerServer(ServerEntity entity) {
        adminLogger.debug("registration of new Server");
        serverDao.addServer(entity);
    }

    /**
     * updating Server
     */
    @Override
    public synchronized void updateServer(ServerEntity entity) {
        adminLogger.debug("updating Server with id: " + entity.getId());
        serverDao.updateServer(entity);
    }

    /**
     * @return List of ServerEntity objects
     */
    @Override
    public List<ServerEntity> getAllServers() {
        adminLogger.debug("retrieving list of servers");
        return serverDao.findAllServers();
    }

    /**
     * deleting Server
     */
    @Override
    public synchronized void deleteServer(Long id) {
        adminLogger.debug("deleting Server with id: " + id);
        serverDao.deleteServer(id);
    }

    /**
     * Updating existing Settings
     * @param model must be SystemSettingsModel type
     */
    @Override
    public synchronized void updateSettings(SystemSettingsModel model) {
        adminLogger.debug("updating Settings");
        settingsDao.updateSettings(model);
    }

    /**
     * Adds new Settings with new Id assignment
     */
    public void addSettings(SystemSettingsModel model) {
        adminLogger.debug("Creating new settings");
        settingsDao.addSettings(model);
    }

    /**
     * Deleting Settings entity
     */
    public synchronized void deleteSettings(String settings_name) {
        adminLogger.debug("Deleting new settings");
        settingsDao.deleteSettings(settings_name);
    }
}
