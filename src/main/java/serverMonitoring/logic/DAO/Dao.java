package serverMonitoring.logic.DAO;

import java.io.Serializable;
import java.util.List;
/*
* Interface to specify list of methods for generic DAO functionality
*/
public interface Dao<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(Serializable key);

    public T find(Serializable key);

    public List<T> findAll();

    public List<T> findAllByParam(String paramName, Object paramValue);
}
