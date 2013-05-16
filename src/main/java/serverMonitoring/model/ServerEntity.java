package serverMonitoring.model;

import serverMonitoring.model.serverStateEnum.ServerState;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Server entity model with constructor
 * validation setting
 * Hibernate table creation settings
 */
@Entity
@Table(name = "server_entity", catalog = "server_monitoring_db")
public class ServerEntity implements Serializable {

    /*
     * a unique identifier of the entity.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /*
     * a unique name for the server in the system.
     */
    //@NotEmpty(message = "{server_name.required}")
    //@Length(min = 5, message = "{server_name.length}")
    //@Pattern(regexp = "^[a-zA-Z0-9.]$", message = "{server_name.content}")
    @Column(name = "server_name", nullable = false)
    private String server_name;

    /*
     *IP address or host name.
     */
    //@NotEmpty(message = "{address.required}")
    //@Length(min = 5, message = "{address.length}")
    //@Pattern(regexp = "^[a-zA-Z0-9.]$", message = "{address.content}")
    @Column(name = "address", nullable = false)
    private String address;

    /*
     * server port:80 by default.
     */
    //@NotEmpty(message = "{port.required}")
    //@Length(min = 5, max = 16, message = "{port.length}")
    //@Pattern(regexp = "^[0-9]{5,10}$", message = "{port.content}")
    @Column(name = "port", nullable = false, length = 16)
    private Integer port = 80;

    /*
     *  resource path by default:"/" .
     */
    //@NotEmpty(message = "{url.required}")
    //@Length(min = 5, max = 16, message = "{url.length}")
    //@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z]).{5,16})", message = "{url.content}")
    @Column(name = "url")
    private String url = "/";

    /*
     * The last state. The possible values ​​are:
     * OK   - server is up and responding correctly
     * WARN - server is running, but returns a response to the HTTP - code different than 200
     * FAIL - the server is not responding, or responds with HTTP code, such as 500
     */
    @Column(name = "state")
    private ServerState state;

    /*
     * headlines of the last server response.
     */
    @Column(name = "response")
    private String response;

    /*
     * date of creation or updating.
     */
    @Column(name = "created", nullable = false, length = 15)
    private Timestamp created;

    /*
     * time of the the last status check.
     */
    @Column(name = "lastCheck", nullable = false, length = 15)
    private Timestamp lastCheck;

    /*
    * If set to "0" - no need to carry out monitoring.
    * 0 = not active
    * 1 = active
    */
    //@NotEmpty(message = "{active.required}")
    //@Length(max = 1, message = "{active.length}")
    //@Pattern(regexp = "^[0,1]{1}$")
    @Column(name = "active", nullable = false)
    private Integer active;

    public ServerEntity() {}

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
