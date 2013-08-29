package serverMonitoring.logic.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.logic.DAO.SettingsDao;
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;

/**
 * Handel's functionality for access with Anonymous role
 */
@Service("AnonymousServiceImpl")
public class AnonymousServiceImpl implements AnonymousService {

    protected static Logger anonymousLogger = Logger.getLogger(AnonymousServiceImpl.class);

    private EmployeeDao employeeDao;

    private SettingsDao settingsDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
        anonymousLogger.debug("retrieving Employee with e-mail: " + email);
        return employeeDao.findByEmail(email);
    }

    /**
     * Updating Employee password if access selected as "active"
     */
    @Override
    public void updateEmployeePassword(EmployeeEntity entity, String newPass) {
        anonymousLogger.debug("updating Employee Password with login: " + entity.getLogin());
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

    /**
     * Retrieves Settings
     * @return System Settings Model object
     */
    @Override
    public SystemSettingsModel getSettingsByName(String name) {
        anonymousLogger.debug("Retrieving settings");
        return settingsDao.getSettingsByName(name);
    }
}
