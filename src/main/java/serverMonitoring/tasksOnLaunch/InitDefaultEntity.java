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

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 *
 * this class responsible for initialisation of default entity
 */
@Component
public class InitDefaultEntity implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger log = Logger.getLogger(this.getClass().getName());
    @Value( "#{applicationProperties['encoding.strengths']}" )
    protected String encodingStrengths;
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(Integer.parseInt(encodingStrengths));
    private java.util.Date date = new java.util.Date();
    @Autowired
    private EmployeeEntity entity = new EmployeeEntity();
    @Autowired
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    /*
     *  settings from application.properties
     */
    @Value( "#{applicationProperties['startup.entity.setAdmin']}" )
    protected String entitySetAdmin;
    @Value( "#{applicationProperties['startup.entity.setActive']}" )
    protected String entitySetActive;
    @Value( "#{applicationProperties['startup.entity.setEmail']}" )
    protected String entitySetEmail;
    @Value( "#{applicationProperties['startup.entity.setEmployee_name']}" )
    protected String entitySetEmployee_name;
    @Value( "#{applicationProperties['startup.entity.setLogin']}" )
    protected String entitySetLogin;
    @Value( "#{applicationProperties['startup.entity.setPassword']}" )
    protected String entitySetPassword;

    /*
     * creating default access entity
     */
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<EmployeeEntity> existing = employeeDao.findAllByParam("login", "admin");
        if (!existing.isEmpty()) {
            return;
        }

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
    }
}
