package serverMonitoring.model.ftl;

import java.util.List;

/**
 * POJO for Server Assignment page
 */
public class ServersListModel {

    private List<String> assignedServers;

    private List<String> availableServers;

    public ServersListModel() {}

    public ServersListModel(List<String> assignedServers, List<String> availableServers) {
        this.assignedServers = assignedServers;
        this.availableServers = availableServers;
    }

    public List<String> getAssignedServers() {
        return assignedServers;
    }

    public void setAssignedServers(List<String> assignedServers) {
        this.assignedServers = assignedServers;
    }

    public List<String> getAvailableServers() {
        return availableServers;
    }

    public void setAvailableServers(List<String> availableServers) {
        this.availableServers = availableServers;
    }
}