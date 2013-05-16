package serverMonitoring.tasksOnLaunch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.DAO.DAOImpl.EmployeeDaoImpl;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import javax.xml.bind.DataBindingException;
import java.sql.Timestamp;
import java.util.List;

/**
 * this class responsible for initialisation of default entity at the start of application
 */
@Component
public class InitDefaultEntity implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger log = Logger.getLogger(this.getClass().getName());
    protected @Value( "#{applicationProperties['encoding.strengths']}" ) String encodingStrengths;
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private java.util.Date date = new java.util.Date();
    private EmployeeEntity entity = new EmployeeEntity();
    @Autowired
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    /*
     *  settings from application.properties
     */
    protected @Value( "#{applicationProperties['startup.entity.setAdmin']}" ) String entitySetAdmin;
    protected @Value( "#{applicationProperties['startup.entity.setActive']}" ) String entitySetActive;
    protected @Value( "#{applicationProperties['startup.entity.setEmail']}" ) String entitySetEmail;
    protected @Value( "#{applicationProperties['startup.entity.setEmployee_name']}" ) String entitySetEmployee_name;
    protected @Value( "#{applicationProperties['startup.entity.setLogin']}" )String entitySetLogin;
    protected @Value( "#{applicationProperties['startup.entity.setPassword']}" ) String entitySetPassword;

    /*
     * creating default access entity
     */
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<EmployeeEntity> existing = employeeDao.findAllByParam("login", "admin");
        if (!existing.isEmpty()) {
            return;
        }

        try {
            entity.setAdmin(Integer.parseInt(entitySetAdmin));
            entity.setActive(Integer.parseInt(entitySetActive));
            entity.setCreated(new Timestamp(date.getTime()));
            entity.setEmail(entitySetEmail);
            entity.setEmployee_name(entitySetEmployee_name);
            entity.setLastLogin(new Timestamp(date.getTime()));
            entity.setLogin(entitySetLogin);
            entity.setPassword(passwordEncoder.encodePassword(entitySetPassword, null));
            employeeDao.save(entity);
            log.debug("successful creation of default access entity");
        } catch (DataBindingException e) {
            log.error(e.getStackTrace());
            throw new ExceptionInInitializerError("creation of default access entity");
        }
    }
}
