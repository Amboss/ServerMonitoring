package serverMonitoring.model;

import serverMonitoring.model.serverStateEnum.ServerState;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Server entity model with constructor
 */
public class ServerEntity implements Serializable {

    //a unique identifier of the entity.
    private Long id;

    //a unique name for the server in the system.
    private String server_name;

    //IP address or host name.
    private String address;

    //server port:80 by default.
    private Integer port = 80;

    //resource path by default:"/".
    private String url = "/";

    //headlines of the last server response.
    private String response;

    //date of creation or updating.
    private Timestamp created;

    //time of the the last status check.
    private Timestamp lastCheck;

    /**
    * The last state. The possible values ​​are:
    * OK   - server is up and responding correctly
    * WARN - server is running, but returns a response to the HTTP - code different than 200
    * FAIL - the server is not responding, or responds with HTTP code, such as 500
    */
    private ServerState state;

    /**
    * If set to "0" - no need to carry out monitoring.
    * 0 = not active
    * 1 = active
    */
    private Integer active;

    public ServerEntity() {
    }

    public ServerEntity(Long id, String server_name, String address, Integer port,
                        String url, ServerState state, String response,
                        Timestamp created, Timestamp lastCheck, Integer active) {
        this.id = id;
        this.server_name = server_name;
        this.address = address;
        this.port = port;
        this.url = url;
        this.state = state;
        this.response = response;
        this.created = created;
        this.lastCheck = lastCheck;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ServerState getState() {
        return state;
    }

    public void setState(ServerState state) {
        this.state = state;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Timestamp lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof ServerEntity)) return false;
        ServerEntity that = (ServerEntity) o;
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ServerEntity{" +
                "id=" + id +
                ", server_name='" + server_name + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", state=" + state +
                ", response='" + response + '\'' +
                ", created=" + created +
                ", lastCheck=" + lastCheck +
                ", active=" + active +
                '}';
    }
}
