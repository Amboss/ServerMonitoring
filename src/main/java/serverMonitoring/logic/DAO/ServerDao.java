package serverMonitoring.logic.DAO;

import serverMonitoring.model.ServerEntity;

import java.util.List;

/**
 * Interface to specify DAO functionality for ServerEntity
 */
public interface ServerDao{

    /**
     * Adds new Server entity with new Id assignment
     */
    public void addServer(ServerEntity entity);

    /**
     * Adds group of Server entities
     */
    public void addGroupOfServers(final List<ServerEntity> entity);

    /**
     * Updating existing Server entity
     */
    public void updateServer(ServerEntity entity);

    /**
     * Deleting existing Server entity
     */
    public void deleteServer(Long id);

    /**
     * Retrieves Server entity by Id
     */
    public ServerEntity findServerById(Long id);

    /**
     * Query retrieves ServerEntity by server name
     */
    public ServerEntity findByServerName(String entity_name);

    /**
     * Retrieves List of Server entity with provided Id
     */
    public List<ServerEntity> findAllByResponsibleId(Long responsible_id);

    /**
     * Retrieves all Server entity
     */
    public List<ServerEntity> findAllServers();


}
