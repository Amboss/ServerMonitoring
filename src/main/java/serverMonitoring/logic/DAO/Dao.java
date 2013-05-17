package serverMonitoring.logic.DAO;

import java.sql.SQLException;
import java.util.List;
/*
* Interface to specify list of methods for generic DAO functionality
*/
public interface Dao<T> {

    public void add(T entity) throws SQLException;

    public void addGroup(final List<T> entity);

    public void update(T entity) throws SQLException;

    public void delete(T entity_id) throws SQLException;

    public T findById(Long entity_id) throws SQLException;

    public List<T> findAll() throws SQLException;
}
