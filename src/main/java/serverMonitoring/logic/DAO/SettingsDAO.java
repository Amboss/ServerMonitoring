package serverMonitoring.logic.DAO;

import serverMonitoring.model.ftl.SystemSettingsModel;

/**
 * Interface to specify DAO functionality for Settings Model
 */
public interface SettingsDAO {

    /**
     * Updating existing Settings
     */
    public void updateSettings(SystemSettingsModel model);

    /**
     * Retrieves Settings
     */
    public SystemSettingsModel getSettings(Long id);
}
