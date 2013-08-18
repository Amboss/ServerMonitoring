package serverMonitoring.model.ftl;

import java.io.Serializable;

/**
 * POJO for System Settings page
 */
public class SystemSettingsModel implements Serializable {

    private Long id;

    private String settings_name;

    private Integer serverScanInterval;

    private Integer timeoutOfRespond;

    private Integer pageReloadTime;

    private String smtpServerHost;

    private Integer smtpServerPort;

    private String username;

    private String password;

    /**
     * Server entity constructor
     */
    public SystemSettingsModel() {
    }

    public SystemSettingsModel(Long id, Integer serverScanInterval, String settings_name,
                               Integer timeoutOfRespond, Integer pageReloadTime,
                               String smtpServerHost, Integer smtpServerPort,
                               String username, String password) {
        this.id = id;
        this.settings_name = settings_name;
        this.serverScanInterval = serverScanInterval;
        this.timeoutOfRespond = timeoutOfRespond;
        this.pageReloadTime = pageReloadTime;
        this.smtpServerHost = smtpServerHost;
        this.smtpServerPort = smtpServerPort;
        this.username = username;
        this.password = password;
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

    public String getSmtpServerHost() {
        return smtpServerHost;
    }

    public void setSmtpServerHost(String smtpServerHost) {
        this.smtpServerHost = smtpServerHost;
    }

    public Integer getSmtpServerPort() {
        return smtpServerPort;
    }

    public void setSmtpServerPort(Integer smtpServerPort) {
        this.smtpServerPort = smtpServerPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SystemSettingsModel{" +
                "id=" + id +
                ", settings_name='" + settings_name + '\'' +
                ", serverScanInterval=" + serverScanInterval +
                ", timeoutOfRespond=" + timeoutOfRespond +
                ", pageReloadTime=" + pageReloadTime +
                ", smtpServerHost='" + smtpServerHost + '\'' +
                ", smtpServerPort=" + smtpServerPort +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
