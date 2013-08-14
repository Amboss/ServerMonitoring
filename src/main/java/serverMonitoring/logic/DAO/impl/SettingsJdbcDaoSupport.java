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
import serverMonitoring.logic.DAO.SettingsDao;
import serverMonitoring.model.ftl.SystemSettingsModel;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class embodies DAO functionality for Settings Model
 */
@Transactional
@Repository
public class SettingsJdbcDaoSupport implements SettingsDao {

    protected static Logger daoSupportLogger = Logger.getLogger(SettingsJdbcDaoSupport.class);

    private SimpleJdbcInsert insertEntity;

    private JdbcTemplate jdbcTemplate;

    private String db_table = "system_settings";

    private String raw_list = "id, " +
            "settings_name, " +
            "scan_interval, " +
            "timeout, " +
            "reload_time, " +
            "smtp_host, " +
            "smtp_port, " +
            "username, " +
            "password";

    private String raw_list_update = "id = ?, " +
            "settings_name = ?, " +
            "scan_interval = ?, " +
            "timeout = ?, " +
            "reload_time = ?, " +
            "smtp_host = ?, " +
            "smtp_port = ?, " +
            "username = ?, " +
            "password = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEntity = new SimpleJdbcInsert(dataSource)
                .withTableName(db_table)
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Adds new Settings with new Id assignment
     */
    @Override
    public void addSettings(SystemSettingsModel model) {
        assert model != null;
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", model.getId())
                    .addValue("settings_name", model.getSettings_name())
                    .addValue("scan_interval", model.getServerScanInterval())
                    .addValue("timeout", model.getTimeoutOfRespond())
                    .addValue("reload_time", model.getPageReloadTime())
                    .addValue("smtp_host", model.getSmtpServerHost())
                    .addValue("smtp_port", model.getSmtpServerPort())
                    .addValue("username", model.getUsername())
                    .addValue("password", model.getPassword());
            if (model.getId() == null) {
                Number newId = insertEntity.executeAndReturnKey(parameters);
                model.setId(newId.longValue());
            } else {
                insertEntity.execute(parameters);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Updating existing Settings
     */
    @Override
    public void updateSettings(SystemSettingsModel model) {
        String query = "UPDATE " + db_table + " SET " + raw_list_update + " WHERE settings_name= ? ";
        SystemSettingsModel dBmodel;
        try {
            assert model.getSettings_name() != null;
            dBmodel = getSettingsByName(model.getSettings_name());
            if (model.getId() == null) {
                model.setId(dBmodel.getId());
            }
            if(model.getSettings_name() == null) {
                model.setSettings_name(dBmodel.getSettings_name());
            }
            if (model.getServerScanInterval() == null) {
                model.setServerScanInterval(dBmodel.getServerScanInterval());
            }
            if (model.getTimeoutOfRespond() == null) {
                model.setTimeoutOfRespond(dBmodel.getTimeoutOfRespond());
            }
            if (model.getPageReloadTime() == null) {
                model.setPageReloadTime(dBmodel.getPageReloadTime());
            }
            if (model.getSmtpServerHost() == null) {
                model.setSmtpServerHost(dBmodel.getSmtpServerHost());
            }
            if (model.getSmtpServerPort() == null) {
                model.setSmtpServerPort(dBmodel.getSmtpServerPort());
            }
            if (model.getUsername() == null) {
                model.setUsername(dBmodel.getUsername());
            }
            if (model.getPassword() == null) {
                model.setPassword(dBmodel.getPassword());
            }
            // creating entity fill in arguments
            Object[] args = {model.getId(),
                    model.getSettings_name(),
                    model.getServerScanInterval(),
                    model.getTimeoutOfRespond(),
                    model.getPageReloadTime(),
                    model.getSmtpServerHost(),
                    model.getSmtpServerPort(),
                    model.getUsername(),
                    model.getPassword(),
                    model.getSettings_name()};
            this.jdbcTemplate.update(query, args);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * Retrieves Settings
     */
    @Override
    public SystemSettingsModel getSettingsByName(String settings_name) {
        assert settings_name != null;
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE settings_name= ?";
        try {
            Object[] args = {settings_name};
            return this.jdbcTemplate.queryForObject(query, args, new SettingsModelMapper());
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Deleting Settings entity
     */
    @Override
    public void deleteSettings(String settings_name) {
        assert settings_name != null;
        String query = "DELETE FROM " + db_table + " WHERE settings_name= ?";
        try {
            Object[] args = {settings_name};
            this.jdbcTemplate.update(query, args);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
     * Supporting inner class for retrieves SystemSettingsModel objects
     * @return SystemSettingsModel object
     */
    private static final class SettingsModelMapper implements RowMapper<SystemSettingsModel> {
        public SystemSettingsModel mapRow(ResultSet rs, int rowNum) {
            try {
                SystemSettingsModel model = new SystemSettingsModel();
                model.setId(rs.getLong("id"));
                model.setSettings_name(rs.getString("settings_name"));
                model.setServerScanInterval(rs.getInt("scan_interval"));
                model.setTimeoutOfRespond(rs.getInt("timeout"));
                model.setPageReloadTime(rs.getInt("reload_time"));
                model.setSmtpServerHost(rs.getString("smtp_host"));
                model.setSmtpServerPort(rs.getInt("smtp_port"));
                model.setUsername(rs.getString("username"));
                model.setPassword(rs.getString("password"));
                return model;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
}
