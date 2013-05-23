package serverMonitoring.logic.dao;

import java.util.List;
/*
* Interface to specify list of methods for generic dao functionality
*/
public interface Dao<T> {

    public void add(T entity);

    public void addGroup(final List<T> entity);

    public void update(T entity);

    void delete(Long entity_id);

    public T findById(Long entity_id);

    public List<T> findAll();
}
