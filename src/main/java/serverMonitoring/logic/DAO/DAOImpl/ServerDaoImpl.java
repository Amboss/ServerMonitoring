package serverMonitoring.logic.DAO.DAOImpl;

import org.springframework.stereotype.Repository;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.model.ServerEntity;

/*
 * This class embodies DAO functionality for ServerEntity
 */
@Repository("serverDaoImpl")
public class ServerDaoImpl extends CustomHibernateDaoSupport<ServerEntity> implements ServerDao {

    public ServerDaoImpl() {
        super(ServerEntity.class);
    }
}
