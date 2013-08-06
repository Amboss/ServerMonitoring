package serverMonitoring.model.ftl;

import java.io.Serializable;

/**
 * System Settings model
 */
public class SystemSettingsModel implements Serializable {

    private Long id;

    private Integer serverScanInterval;

    private Integer timeoutOfRespound;

    private Integer pageReloadTime;

    private String smtpServerAdress;

    private Integer smtpServerPort;

    /**
     * Server entity constructor
     */
    public SystemSettingsModel() {
    }

    public SystemSettingsModel(Long id, Integer serverScanInterval,
                               Integer timeoutOfRespound, Integer pageReloadTime,
                               String smtpServerAdress, Integer smtpServerPort) {
        this.id = id;
        this.serverScanInterval = serverScanInterval;
        this.timeoutOfRespound = timeoutOfRespound;
        this.pageReloadTime = pageReloadTime;
        this.smtpServerAdress = smtpServerAdress;
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

    public Integer getServerScanInterval() {
        return serverScanInterval;
    }

    public void setServerScanInterval(Integer serverScanInterval) {
        this.serverScanInterval = serverScanInterval;
    }

    public Integer getTimeoutOfRespound() {
        return timeoutOfRespound;
    }

    public void setTimeoutOfRespound(Integer timeoutOfRespound) {
        this.timeoutOfRespound = timeoutOfRespound;
    }

    public Integer getPageReloadTime() {
        return pageReloadTime;
    }

    public void setPageReloadTime(Integer pageReloadTime) {
        this.pageReloadTime = pageReloadTime;
    }

    public String getSmtpServerAdress() {
        return smtpServerAdress;
    }

    public void setSmtpServerAdress(String smtpServerAdress) {
        this.smtpServerAdress = smtpServerAdress;
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
                ", timeoutOfRespound=" + timeoutOfRespound +
                ", pageReloadTime=" + pageReloadTime +
                ", smtpServerAdress='" + smtpServerAdress + '\'' +
                ", smtpServerPort=" + smtpServerPort +
                '}';
    }
}
