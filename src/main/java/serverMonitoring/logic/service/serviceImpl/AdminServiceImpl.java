package serverMonitoring.logic.service.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.DAOImpl.EmployeeJdbcDaoSupport;
import serverMonitoring.logic.DAO.DAOImpl.ServerJdbcDaoSupport;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * This class responsible for functionality of user with ROLE_ADMIN access
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl extends EmployeeServiceImpl implements AdminService {

    protected static Logger adminLogger = Logger.getLogger("AdminServiceImpl");
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
     * registration of new Employee
     * @return EmployeeEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public EmployeeEntity registerEmployee(EmployeeEntity entity) {
        String str = "of new Employee";
        adminLogger.debug("registration " + str);
        try {
            employeeDao.add(entity);
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error in registration " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * updating Employee
     * @return EmployeeEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public EmployeeEntity updateEmployee(EmployeeEntity entity) {
        String str = "Employee with id: " + entity.getId();
        adminLogger.debug("updating " + str);
        try {
            employeeDao.update(entity);
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error in update of " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Retrieving list of all employees
     *
     * @return List of EmployeeEntity objects
     */
    @Override
    @Secured("ROLE_ADMIN")
    public List<EmployeeEntity> getAllEmployee() {

        adminLogger.debug("retrieving list of servers");
        List<EmployeeEntity> employeeList = null;
        try {
            employeeList = employeeDao.findAll();
            for (EmployeeEntity aEmployeeList : employeeList) {
                EmployeeEntity entity = aEmployeeList;
                System.out.println(entity);
            }
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error while retrieving list of servers");
            e.printStackTrace();
        }
        return employeeList;
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
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error while deleting " + str);
            e.printStackTrace();
        }
    }

    /**
     * registration of new Server
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public ServerEntity registerServer(ServerEntity entity) {
        String str = "registration of new Server";
        adminLogger.debug(str);
        try {
            serverDao.add(entity);
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error in " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * updating Server
     * @return ServerEntity object
     */
    @Override
    @Secured("ROLE_ADMIN")
    public ServerEntity updateServer(ServerEntity entity) {
        String str = "Server with id: " + entity.getId();
        adminLogger.debug("updating " + str);
        try {
            serverDao.update(entity);
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error while updating of " + str);
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * @return List of ServerEntity objects
     */
    @Override
    @Secured("ROLE_ADMIN")
    public List<ServerEntity> getAllServers() {

        adminLogger.debug("retrieving list of servers");
        List<ServerEntity> serverList = null;
        try {
            serverList = serverDao.findAll();
//            for (ServerEntity aServerList : serverList) {
//                ServerEntity serverEntity = aServerList;
//                System.out.println(serverEntity);
//            }

        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error while retrieving list of servers");
            e.printStackTrace();
        }
        return serverList;
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
        } catch (SQLException | NullPointerException e) {
            adminLogger.error("error while deleting  " + str);
            e.printStackTrace();
        }
    }


    private static Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }
}
