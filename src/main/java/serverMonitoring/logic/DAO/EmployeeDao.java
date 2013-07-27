package serverMonitoring.logic.DAO;

import serverMonitoring.model.EmployeeEntity;

import java.util.List;

/**
 * Interface to specify DAO functionality for EmployeeEntity
 */
public interface EmployeeDao {

    /**
     * Adds new Employee entity with new Id assignment
     */
    public void add(EmployeeEntity entity);

    /**
     * Adds group of Employee entities
     */
    public void addGroup(final List<EmployeeEntity> entity);

    /**
     * Updating existing Employee entity
     */
    public void update(EmployeeEntity entity);

    /**
     * Deleting existing Employee entity
     */
    public void delete(Long entity_id);

    /**
     * Retrieves EmployeeEntity entity by Id
     */
    public EmployeeEntity findById(Long entity_id);

    /**
     * Retrieves all EmployeeEntity entity
     */
    public List<EmployeeEntity> findAll();

    /**
     * Query retrieves EmployeeEntity entity by login
     */
    public EmployeeEntity findByLogin(String entity_login);

    /**
     * Query retrieves EmployeeEntity entity by E-mail
     */
    public EmployeeEntity findByEmail(String email);
}