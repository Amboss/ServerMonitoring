package serverMonitoring.logic.DAO.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import serverMonitoring.logic.DAO.SettingsDAO;
import serverMonitoring.model.ftl.SystemSettingsModel;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class embodies DAO functionality for Settings Model
 */
public class SettingsJdbcDaoSupport implements SettingsDAO {

    protected static Logger daoSupportLogger = Logger.getLogger(SettingsJdbcDaoSupport.class);
    private SimpleJdbcInsert insertEntity;
    private JdbcTemplate jdbcTemplate;
    private String db_table = "system_settings";
    private String raw_list = "id, scan_interval, timeout, reload_time, smtp_adress, smtp_port";
    private String raw_list_update = "id = ?, scan_interval = ?, timeout = ?, reload_time = ?, " +
            "smtp_adress = ?, smtp_port = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEntity = new SimpleJdbcInsert(dataSource)
                .withTableName(db_table)
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Updating existing Settings
     */
    @Override
    public void updateSettings(SystemSettingsModel model) {
        String query = "UPDATE " + db_table + " SET " + raw_list_update + " WHERE id = ? ";
        SystemSettingsModel dBmodel;
        try {
            assert model.getId() != null;
            dBmodel = getSettings(model.getId());

            if (model.getServerScanInterval() == null) {
                model.setServerScanInterval(dBmodel.getServerScanInterval());
            }
            if (model.getTimeoutOfRespound() == null) {
                model.setTimeoutOfRespound(dBmodel.getTimeoutOfRespound());
            }
            if (model.getPageReloadTime() == null) {
                model.setPageReloadTime(dBmodel.getPageReloadTime());
            }
            if (model.getSmtpServerAdress() == null) {
                model.setSmtpServerAdress(dBmodel.getSmtpServerAdress());
            }
            if (model.getSmtpServerPort() == null) {
                model.setSmtpServerPort(dBmodel.getSmtpServerPort());
            }
            // creating entity fill in arguments
            Object[] args = {model.getId(),
                    model.getServerScanInterval(),
                    model.getTimeoutOfRespound(),
                    model.getPageReloadTime(),
                    model.getSmtpServerAdress(),
                    model.getSmtpServerPort(),
                    model.getId()};
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
    public SystemSettingsModel getSettings(Long id) {
        assert id != null;
        String query = "SELECT " + raw_list + " FROM " + db_table + " WHERE id= ?";
        try {
            Object[] args = {id};
            return this.jdbcTemplate.queryForObject(query, args, new SettingsModelMapper());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    /*
   * Supporting inner class for retrieves EmployeeEntity objects
   *
   * @return EmployeeEntity object
   */
    private static final class SettingsModelMapper implements RowMapper<SystemSettingsModel> {
        public SystemSettingsModel mapRow(ResultSet rs, int rowNum) {
            try {
                SystemSettingsModel model = new SystemSettingsModel();
                model.setId(rs.getLong("id"));
                model.setServerScanInterval(rs.getInt("serverScanInterval"));
                model.setTimeoutOfRespound(rs.getInt("timeoutOfRespound"));
                model.setPageReloadTime(rs.getInt("pageReloadTime"));
                model.setSmtpServerAdress(rs.getString("smtpServerAdress"));
                model.setSmtpServerPort(rs.getInt("smtpServerPort"));
                return model;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }
}
