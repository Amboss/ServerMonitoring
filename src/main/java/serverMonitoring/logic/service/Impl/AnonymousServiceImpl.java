package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.EmployeeEntity;

/**
 * Handel's functionality for access with Anonymous role
 */
@Service("AnonymousServiceImpl")
public class AnonymousServiceImpl implements AnonymousService {

    protected static Logger anonymousAccessLogger = Logger.getLogger(AnonymousServiceImpl.class);
    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * Retrieves EmployeeEntity entity by E-mail
     *
     * @return Employee Entity object
     */
    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        anonymousAccessLogger.debug("retrieving Employee with e-mail: " + email);
        return employeeDao.findByEmail(email);
    }

    /**
     * Updating Employee password if access selected as "active"
     */
    @Override
    public void updateEmployeePassword(EmployeeEntity entity, String newPass) {
        anonymousAccessLogger.debug("updating Employee Password with login: " + entity.getLogin());
        // selecting target entity
        EmployeeEntity entity2 = employeeDao.findByLogin(entity.getLogin());

        if (!entity2.getActive().equals(0)) {
            // hashing new password
            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
            // setting new password
            entity2.setPassword(passwordEncoder.encodePassword(newPass, null));
            employeeDao.update(entity2);
        }
    }
}
