package serverMonitoring.model.ftl;

import java.util.List;

/**
 * POJO for Server Assignment page
 */
public class ServersListModel {

    private List<Long> assignedServers;

    private List<Long> availableServers;

    public ServersListModel() {}

    public ServersListModel(List<Long> assignedServers, List<Long> availableServers) {
        this.assignedServers = assignedServers;
        this.availableServers = availableServers;
    }

    public List<Long> getAssignedServers() {
        return assignedServers;
    }

    public void setAssignedServers(List<Long> assignedServers) {
        this.assignedServers = assignedServers;
    }

    public List<Long> getAvailableServers() {
        return availableServers;
    }

    public void setAvailableServers(List<Long> availableServers) {
        this.availableServers = availableServers;
    }
}