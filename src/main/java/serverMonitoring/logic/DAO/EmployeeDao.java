package serverMonitoring.logic.dao;

import serverMonitoring.model.EmployeeEntity;

/**
 * Interface to specify dao functionality for EmployeeEntity
 */
public interface EmployeeDao extends Dao<EmployeeEntity> {

    // Query retrieves EmployeeEntity entity by login for authentication module
    public EmployeeEntity findByLogin(String entity_login);


}