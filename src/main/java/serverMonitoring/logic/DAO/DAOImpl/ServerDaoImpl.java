package serverMonitoring.logic.DAO.DAOImpl;

import org.springframework.stereotype.Repository;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.model.ServerEntity;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */
@Repository("serverDaoImpl")
public class ServerDaoImpl extends CustomHibernateDaoSupport<ServerEntity> implements ServerDao {

    public ServerDaoImpl() {
        super(ServerEntity.class);
    }
}
