package serverMonitoring.logic.DAO.DAOImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.DAO.Dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 *
 * CustomHibernateDaoSupport with sessionFactory insted HibernateDaoSupport
 */
@Transactional
public class CustomHibernateDaoSupport<T> implements Dao<T> {
    protected static Logger daoSupportLogger = Logger.getLogger("CustomHibernateDaoSupport");
    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public CustomHibernateDaoSupport(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory  getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void save(T entity) {
        getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    @Override
    public void delete(Serializable key) {
        Object entity = getSessionFactory().getCurrentSession().get(clazz, key);
        if (entity != null) {
            getSessionFactory().getCurrentSession().delete(entity);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T find(Serializable key) {
        return (T) getSessionFactory().getCurrentSession().get(clazz, key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSessionFactory().getCurrentSession().createCriteria(clazz).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllByParam(final String paramName, final Object paramValue) {
        return getSessionFactory().getCurrentSession().createCriteria(clazz)
                .add(Restrictions.eq(paramName, paramValue))
                .list();
    }
}