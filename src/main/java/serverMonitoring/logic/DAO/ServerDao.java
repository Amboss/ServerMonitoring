package serverMonitoring.logic.DAO;

import serverMonitoring.model.ServerEntity;

/**
 * Interface to specify DAO functionality for ServerEntity
 */
public interface ServerDao extends Dao<ServerEntity> {

    /**
     * Query retrieves ServerEntity entity by server name
     */
    public ServerEntity findByServerName(String entity_name);
}
