package serverMonitoring.logic.service.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
@Service("adminService")
public class AdminServiceImpl extends EmployeeServiceImpl implements AdminService {

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
     *
     * @return EmployeeEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public EmployeeEntity registerEmployee(EmployeeEntity entity) {
        String str = "of new Employee";
        adminLogger.debug("registration " + str);
        try {
            employeeDao.save(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error in registration " + str);
        }
        return entity;
    }

    /**
     * updating Employee
     *
     * @return EmployeeEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public EmployeeEntity updateEmployee(EmployeeEntity entity) {
        String str = "Employee with id: " + entity.getId();
        adminLogger.debug("updating " + str);
        try {
            employeeDao.update(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error in update of " + str);
        }
        return entity;
    }

    /**
     * deleting Employee
     */
    @Override
    @Secured("ROLE_ADMIN")
    public void deleteEmployee(EmployeeEntity entity) {
        String str = "Employee with id: " + entity.getId();
        adminLogger.debug("deleting " + str);
        try {
            employeeDao.delete(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error while deleting " + str);
        }
    }

    /**
     * registration of new Server
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public ServerEntity registerServer(ServerEntity entity) {
        String str = "registration of new Server";
        adminLogger.debug(str);
        try {
            serverDao.save(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error in " + str);
        }
        return entity;
    }

    /**
     * updating Server
     *
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public ServerEntity updateServer(ServerEntity entity) {
        String str = "Server with id: " + entity.getId();
        adminLogger.debug("updating " + str);
        try {
            serverDao.update(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error while updating of " + str);
        }
        return entity;
    }

    /**
     * deleting Server
     */
    @Override
    @Secured("ROLE_ADMIN")
    public void deleteServer(ServerEntity entity) {
        String str = "Server with id: " + entity.getId();
        adminLogger.debug("deleting " + str);
        try {
            serverDao.delete(entity);
        } catch (NullPointerException e) {
            adminLogger.error("error while deleting  " + str);
        }
    }

}
