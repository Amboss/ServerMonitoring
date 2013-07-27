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
    public void add(ServerEntity entity);

    /**
     * Adds group of Server entities
     */
    public void addGroup(final List<ServerEntity> entity);

    /**
     * Updating existing Server entity
     */
    public void update(ServerEntity entity);

//    /**
//     * Retrieves Server entity entity by Id
//     */
//    public ServerEntity findById(Long entity_id);

    /**
     * Query retrieves ServerEntity entity by server name
     */
    public ServerEntity findByServerName(String entity_name);

    /**
     * Retrieves List of Server entity with provided Id
     */
    public List<ServerEntity> findAllById(Long entity_id);

    /**
     * Retrieves all Server entity entity
     */
    public List<ServerEntity> findAll();

    /**
     * Deleting existing Server entity
     */
    public void delete(String server_name);
}
