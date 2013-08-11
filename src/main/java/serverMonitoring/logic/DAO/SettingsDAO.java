package serverMonitoring.logic.DAO;

import serverMonitoring.model.ftl.SystemSettingsModel;

/**
 * Interface to specify DAO functionality for Settings Model
 */
public interface SettingsDao {

    /**
     * Adds new Settings with new Id assignment
     */
    public void addSettings(SystemSettingsModel model);

    /**
     * Updating existing Settings
     */
    public void updateSettings(SystemSettingsModel model);

    /**
     * Retrieves Settings
     */
    public SystemSettingsModel getSettingsByName(String name) ;

    /**
     * Deleting Settings entity
     */
    public void deleteSettings(String settings_name);
}
