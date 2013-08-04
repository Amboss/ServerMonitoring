package serverMonitoring.logic.DAO.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.DAO.ServerDao;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class embodies DAO functionality for ServerEntity
 */
@Transactional
@Repository
public class ServerJdbcDaoSupport implements ServerDao {

    protected static Logger daoSupportLogger = Logger.getLogger("ServerJdbcDaoSupport");
    private SimpleJdbcInsert insertEntity;
    private JdbcTemplate jdbcTemplate;

    private String nullError = "ServerEntity entity is empty!";
    private String db_table = "server_entity";
    private String raw_list = "id, server_name, address, port, url, state, response, created, lastCheck, active, responsible";
    private String raw_list_update = "id = ?, server_name = ?, address = ?, port = ?, url = ?, " +
            "state = ?, response = ?, created = ?, lastCheck = ?, active = ?, responsible = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEntity = new SimpleJdbcInsert(dataSource)
                .withTableName(db_table)
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Adds new Server entity with new Id assignment
     */
    @Override
    public void addServer(ServerEntity entity) {
        assert entity != null;
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", entity.getId())
                    .addValue("server_name", entity.getServer_name())
                    .addValue("address", entity.getAddress())
                    .addValue("port", entity.getPort())
                    .addValue("url", entity.getUrl())
                    .addValue("state", entity.getState())
                    .addValue("response", entity.getResponse())
                    .addValue("created", entity.getCreated())
                    .addValue("lastCheck", entity.getLastCheck())
                    .addValue("active", entity.getActive())
                    .addValue("responsible", entity.getResponsible());
            if (entity.getId() == null) {
                Number newId = insertEntity.executeAndReturnKey(parameters);
                entity.setId(newId.longValue());
            } else {
                insertEntity.execute(parameters);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Adds group of Server entities
     */
    @Override
    public void addGroupOfServers(final List<ServerEntity> entity) {
        assert entity != null;
        String query = "INSERT INTO " + db_table + " (" + raw_list + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            List<Object[]> parameters = new ArrayList<>();
            for (ServerEntity foo : entity) {
                parameters.add(new Object[]{foo.getId(),
                        foo.getServer_name(),
                        foo.getAddress(),
                        foo.getPort(),
                        foo.getUrl(),
                        ServerState.getStringFromEnum(foo.getState()),
                        foo.getResponse(),
                        foo.getCreated(),
                        foo.getLastCheck(),
                        foo.getActive(),
                        foo.getResponsible()});
            }
            this.jdbcTemplate.batchUpdate(query, parameters);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Updating existing Server entity
     */
    @Override
    public void updateServer(ServerEntity entity) {
        assert entity.getId() != null;

        // updating with full list of rows
        String query = "UPDATE " + db_table + " SET " + raw_list_update + " WHERE id = ? ";
        try {
            // selecting existent entity & replacing null with existent match
            ServerEntity entityInDB = findServerById(entity.getId());
            assert entityInDB != null;

            if (entity.getId() == null) {
                entity.setId(entityInDB.getId());
            }
            if (entity.getServer_name() == null) {
                entity.setServer_name(entityInDB.getServer_name());
            }
            if (entity.getAddress() == null) {
                entity.setAddress(entityInDB.getAddress());
            }
            if (entity.getPort() == null) {
                entity.setPort(entityInDB.getPort());
            }
            if (entity.getUrl() == null) {
                entity.setUrl(entityInDB.getUrl());
            }
            if (entity.getState() == null) {
                entity.setState(entityInDB.getState());
            }
            if (entity.getResponse() == null) {
                entity.setResponse(entityInDB.getResponse());
            }
            if (entity.getCreated() == null) {
                entity.setCreated(entityInDB.getCreated());
            }
            if (entity.getLastCheck() == null) {
                entity.setLastCheck(entityInDB.getLastCheck());
            }
            if (entity.getActive() == null) {
                entity.setActive(entityInDB.getActive());
            }
            if (entity.getResponsible() == null) {
                entity.setResponsible(entityInDB.getResponsible());
            }
            // creating entity fill in arguments
            Object[] args = {entity.getId(),
                    entity.getServer_name(),
                    entity.getAddress(),
                    entity.getPort(),
                    entity.getUrl(),
                    ServerState.getStringFromEnum(entity.getState()),
                    entity.getResponse(),
                    entity.getCreated(),
                    entity.getLastCheck(),
                    entity.getActive(),
                    entity.getResponsible(),
                    entity.getId()};
            this.jdbcTemplate.update(query, args);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Deleting existing Server entity
     */
    @Override
    public void deleteServer(Long id) {
        assert id != null;
        String query = "DELETE FROM " + db_table + " WHERE id= ?";
        try {
            Object[] args = {id};
            this.jdbcTemplate.update(query, args);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves Server entity by Id
     */
    public ServerEntity findServerById(Long id) {
        assert id != null;
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE id= ?";
        try {
            Object[] args = {id};
            return this.jdbcTemplate.queryForObject(query, args, new EmployeeEntityMapper());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves List of Server entity with provided Id
     *
     * @return List<ServerEntity>
     */
    @Override
    public List<ServerEntity> findAllByResponsibleId(Long responsible_id) {
        assert responsible_id != null;
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE responsible= ?";
        try {
            Object[] args = {responsible_id};
            return this.jdbcTemplate.query(query, args, new EmployeeEntityMapper());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves ServerEntity entity by Server Name
     *
     * @return ServerEntity object
     */
    @Override
    public ServerEntity findByServerName(String server_name) {
        assert server_name != null;
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE server_name= ?";
        try {
            Object[] args = {server_name};
            return this.jdbcTemplate.queryForObject(query, args, new EmployeeEntityMapper());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves all Server entity entity
     *
     * @return ServerEntity list
     */
    @Override
    public List<ServerEntity> findAllServers() {
        String query = "SELECT * FROM " + db_table;
        try {
            return this.jdbcTemplate.query(query, new EmployeeEntityMapper());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /*
    * Supporting inner class for retrieves EmployeeEntity objects
    *
    * @return EmployeeEntity object
    */
    private static final class EmployeeEntityMapper implements RowMapper<ServerEntity> {
        public ServerEntity mapRow(ResultSet rs, int rowNum) {
            try {
                ServerEntity entity = new ServerEntity();
                entity.setId(rs.getLong("id"));
                entity.setServer_name(rs.getString("server_name"));
                entity.setAddress(rs.getString("address"));
                entity.setPort(rs.getInt("port"));
                entity.setUrl(rs.getString("url"));
                entity.setState(ServerState.getEnumFromString(rs.getString("state")));
                entity.setResponse(rs.getString("response"));
                entity.setCreated(rs.getTimestamp("created"));
                entity.setLastCheck(rs.getTimestamp("lastCheck"));
                entity.setActive(rs.getInt("active"));
                entity.setResponsible(rs.getLong("responsible"));
                return entity;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }
}
