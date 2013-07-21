package serverMonitoring.logic.DAO;

import serverMonitoring.model.ServerEntity;

import java.util.List;

/**
 * Interface to specify DAO functionality for ServerEntity
 */
public interface ServerDao extends Dao<ServerEntity> {

    /**
     * Query retrieves ServerEntity entity by server name
     */
    public ServerEntity findByServerName(String entity_name);

    /**
     * Retrieves List of Server entity with provided Id
     */
    public List<ServerEntity> findAllById(Long entity_id);
}
