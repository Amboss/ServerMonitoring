package serverMonitoring.logic.DAO.DAOImpl;

import org.springframework.stereotype.Repository;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
@Repository("employeeDAOImpl")
public class EmployeeDaoImpl extends CustomHibernateDaoSupport<EmployeeEntity> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }
}