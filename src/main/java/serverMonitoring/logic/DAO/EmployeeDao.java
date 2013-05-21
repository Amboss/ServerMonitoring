package serverMonitoring.logic.DAO;

import serverMonitoring.model.EmployeeEntity;

import java.sql.SQLException;

/**
 * Interface to specify DAO functionality for EmployeeEntity
 */
public interface EmployeeDao extends Dao<EmployeeEntity> {

    // Query retrieves EmployeeEntity entity by login for authentication module
    public EmployeeEntity findByLogin(String entity_login) throws SQLException;


}