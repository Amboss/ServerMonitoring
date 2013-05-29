package serverMonitoring.logic.DAO;

import serverMonitoring.model.EmployeeEntity;

/**
 * Interface to specify DAO functionality for EmployeeEntity
 */
public interface EmployeeDao extends Dao<EmployeeEntity> {

    /**
     * Query retrieves EmployeeEntity entity by login
     */
    public EmployeeEntity findByLogin(String entity_login);


}