package serverMonitoring.tasksOnLaunch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import serverMonitoring.dataBase.CreateDataBase;
import serverMonitoring.dataBase.createDataBase.CreateDataBaseImpl;
import serverMonitoring.logic.DAO.DAOImpl.EmployeeJdbcDaoSupport;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * this class responsible for initialisation of default entity at the start of application
 */
@Component
public class InitDefaultEntity {
    protected final Logger log = Logger.getLogger(this.getClass().getName());

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private java.util.Date date = new java.util.Date();
    private EmployeeEntity entity = new EmployeeEntity();
    @Autowired
    private EmployeeDao employeeDao = new EmployeeJdbcDaoSupport();
    private CreateDataBase createDataBase = new CreateDataBaseImpl();

    /*
     * creating default access entity
     */
    @PostConstruct
    public void onApplicationEvent() {
        // initialisation of Data Base
        createDataBase.getDBExistsConfirmation();

        // check for existence of default entity
        EmployeeEntity existing = null;
        try {
            existing = employeeDao.findByLogin("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (existing == null) {
            try {
                entity.setAdmin(1);
                entity.setActive(1);
                entity.setCreated(new Timestamp(date.getTime()));
                entity.setEmail("newMail@mail.com");
                entity.setEmployee_name("Default_name");
                entity.setLastLogin(new Timestamp(date.getTime()));
                entity.setLogin("admin");
                entity.setPassword(passwordEncoder.encodePassword("admin", null));
                employeeDao.add(entity);
                log.debug("successful creation of default access entity");
            } catch (SQLException e) {
                log.error(e.getStackTrace());
                throw new ExceptionInInitializerError("creation of default access entity");
            }
        }
    }
}
