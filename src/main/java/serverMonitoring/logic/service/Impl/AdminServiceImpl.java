package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class responsible for functionality of user with ROLE_ADMIN access
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    protected static Logger adminLogger = Logger.getLogger("AdminServiceImpl");
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
    public void updateEmployee(EmployeeEntity entity) {
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
    public void deleteEmployee(Long entity_id) {
        adminLogger.debug("deleting Employee with id: " + Long.toString(entity_id));
        employeeDao.delete(entity_id);
    }

    /**
     * registration of new Server
     */
    @Override
    public void registerServer(ServerEntity entity) {
        adminLogger.debug("registration of new Server");
        serverDao.add(entity);
    }

    /**
     * updating Server
     */
    @Override
    public void updateServer(ServerEntity entity) {
        adminLogger.debug("updating Server with id: " + entity.getId());
        serverDao.update(entity);
    }

    /**
     * @return List of ServerEntity objects
     */
    @Override
    public List<ServerEntity> getAllServers() {
        adminLogger.debug("retrieving list of servers");
        return serverDao.findAll();
    }

    /**
     * deleting Server
     */
    @Override
    public void deleteServer(Long entity_id) {
        adminLogger.debug("deleting Server with id: " + entity_id);
        serverDao.delete(entity_id);
    }

    /*
     * getting current date & time for SQL
     */
    private static Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }
}
