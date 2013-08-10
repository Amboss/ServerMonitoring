package serverMonitoring.model.ftl;

import java.io.Serializable;

/**
 * System Settings model
 */
public class SystemSettingsModel implements Serializable {

    private Long id;

    private String settings_name;

    private Integer serverScanInterval;

    private Integer timeoutOfRespond;

    private Integer pageReloadTime;

    private String smtpServerAddress;

    private Integer smtpServerPort;

    /**
     * Server entity constructor
     */
    public SystemSettingsModel() {
    }

    public SystemSettingsModel(Long id, Integer serverScanInterval, String settings_name,
                               Integer timeoutOfRespond, Integer pageReloadTime,
                               String smtpServerAddress, Integer smtpServerPort) {
        this.id = id;
        this.settings_name = settings_name;
        this.serverScanInterval = serverScanInterval;
        this.timeoutOfRespond = timeoutOfRespond;
        this.pageReloadTime = pageReloadTime;
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }

    /**
     * getters & setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettings_name() {
        return settings_name;
    }

    public void setSettings_name(String settings_name) {
        this.settings_name = settings_name;
    }

    public Integer getServerScanInterval() {
        return serverScanInterval;
    }

    public void setServerScanInterval(Integer serverScanInterval) {
        this.serverScanInterval = serverScanInterval;
    }

    public Integer getTimeoutOfRespond() {
        return timeoutOfRespond;
    }

    public void setTimeoutOfRespond(Integer timeoutOfRespond) {
        this.timeoutOfRespond = timeoutOfRespond;
    }

    public Integer getPageReloadTime() {
        return pageReloadTime;
    }

    public void setPageReloadTime(Integer pageReloadTime) {
        this.pageReloadTime = pageReloadTime;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public void setSmtpServerAddress(String smtpServerAddress) {
        this.smtpServerAddress = smtpServerAddress;
    }

    public Integer getSmtpServerPort() {
        return smtpServerPort;
    }

    public void setSmtpServerPort(Integer smtpServerPort) {
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public String toString() {
        return "SystemSettingsModel{" +
                "serverScanInterval=" + serverScanInterval +
                ", timeoutOfRespond=" + timeoutOfRespond +
                ", pageReloadTime=" + pageReloadTime +
                ", smtpServerAddress='" + smtpServerAddress + '\'' +
                ", smtpServerPort=" + smtpServerPort +
                '}';
    }
}
