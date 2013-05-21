package serverMonitoring.logic.DAO.DAOImpl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.DAO.EmployeeDao;
import serverMonitoring.model.EmployeeEntity;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class embodies DAO functionality for EmployeeEntity
 */
@Transactional
@Repository("EmployeeJdbcDaoSupport")
public class EmployeeJdbcDaoSupport extends JdbcDaoSupport implements EmployeeDao {

    protected static Logger daoSupportLogger = Logger.getLogger("EmployeeJdbcDaoSupport");
    private SimpleJdbcInsert insertEntity;
    private JdbcTemplate jdbcTemplate;
    private String nullError = "EmployeeEntity entity is empty!";
    private String db_table = "employee_entity";
    private String raw_list = "id, employee_name, login, password, email, created, lastLogin, active, admin";
    private String raw_list_update = "(id = ?, employee_name = ?, login = ?, password = ?, " +
            "email = ?, created = ?, lastLogin = ?, active = ?, admin = ?)";
    private String raw_value = "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Resource(name = "dataSource")
    public void initDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEntity = new SimpleJdbcInsert(dataSource)
                .withTableName(db_table)
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Adds new Employee entity with new Id assignment
     */
    @Override
    public void add(EmployeeEntity entity) throws SQLException {
        if (entity != null) {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("employee_name", entity.getEmployee_name())
                    .addValue("login", entity.getLogin())
                    .addValue("password", entity.getPassword())
                    .addValue("email", entity.getEmail())
                    .addValue("created", entity.getCreated())
                    .addValue("lastLogin", entity.getLastLogin())
                    .addValue("active", entity.getActive())
                    .addValue("admin", entity.getAdmin());
            Number newId = insertEntity.executeAndReturnKey(parameters);
            entity.setId(newId.longValue());
        } else {
            throw new NullPointerException(nullError);
        }
    }

    /**
     * Adds group of Employee entities
     */
    @Override
    public void addGroup(final List<EmployeeEntity> entity) {
        String query = "INSERT INTO " + db_table + raw_list + " VALUES " + raw_value;
        if (entity != null) {
            List<Object[]> parameters = new ArrayList<Object[]>();
            for (EmployeeEntity foo : entity) {
                parameters.add(new Object[]{foo.getEmployee_name(),
                        foo.getLogin(),
                        foo.getPassword(),
                        foo.getEmail(),
                        foo.getCreated(),
                        foo.getLastLogin(),
                        foo.getActive(),
                        foo.getAdmin(),
                        foo.getId()});
            }
            this.getJdbcTemplate().batchUpdate(query, parameters);
        } else {
            throw new NullPointerException(nullError);
        }
    }

    /**
     * Updating existing Employee entity
     */
    @Override
    public void update(EmployeeEntity entity) throws SQLException {
        String query = "UPDATE " + db_table + " SET " + raw_list_update + " where id = ?";
        if (entity != null) {Object[] args = {entity.getEmployee_name(),
                    entity.getLogin(),
                    entity.getPassword(),
                    entity.getEmail(),
                    entity.getCreated(),
                    entity.getLastLogin(),
                    entity.getActive(),
                    entity.getAdmin(),
                    entity.getId()};
            this.getJdbcTemplate().update(query, args);
        } else {
            throw new NullPointerException(nullError);
        }
    }

    /**
     * Deleting existing Employee entity
     */
    @Override
    public void delete(Long entity_id) throws SQLException {
        String query = "delete from " + db_table + " where id = ?";
        if (entity_id != null) {
            Object[] args = {entity_id};
            this.getJdbcTemplate().update(query, args);
        } else {
            throw new NullPointerException(nullError);
        }
    }

    /**
     * Retrieves EmployeeEntity entity by Id
     *
     * @return EmployeeEntity object
     */
    @Override
    public EmployeeEntity findById(Long entity_id) throws SQLException {
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE id = ?";
        assert (entity_id != null);
        EmployeeEntity entity = null;
        try {
            entity = this.jdbcTemplate.queryForObject(query, new Object[]{1212L}, new EmployeeEntityMapper());
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return entity;
    }

    /**
     * Retrieves EmployeeEntity entity by login
     *
     * @return EmployeeEntity object
     */
    @Override
    public EmployeeEntity findByLogin(String entity_login) throws SQLException {
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE login = ?";
        assert (entity_login != null);
        EmployeeEntity entity = null;
        try {
            entity = this.jdbcTemplate.queryForObject(query, new Object[]{1212L}, new EmployeeEntityMapper());
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return entity;
    }

    /**
     * Retrieves all EmployeeEntity entity
     *
     * @return EmployeeEntity list
     */
    @Override
    public List<EmployeeEntity> findAll() throws SQLException {
        String query = "SELECT * FROM " + db_table;
        return this.jdbcTemplate.query(query, new EmployeeEntityMapper());
    }

    /*
     * Supporting inner class for retrieves EmployeeEntity objects
     *
     * @return EmployeeEntity object
     */
    private static final class EmployeeEntityMapper implements RowMapper<EmployeeEntity> {
        public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeEntity entity = new EmployeeEntity();
                entity.setEmployee_name(rs.getString("employee_name"));
                entity.setLogin(rs.getString("login"));
                entity.setPassword(rs.getString("password"));
                entity.setEmail(rs.getString("email"));
                entity.setCreated(rs.getTimestamp("created"));
                entity.setLastLogin(rs.getTimestamp("lastLogin"));
                entity.setActive(rs.getInt("active"));
                entity.setAdmin(rs.getInt("admin"));
            return entity;
        }
    }
}

