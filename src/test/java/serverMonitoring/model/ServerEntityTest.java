package serverMonitoring.model;

import org.junit.Test;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link ServerEntity} class.
 */
public class ServerEntityTest {
    ServerEntity entity = new ServerEntity();
    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());

    @Test
    public void testSetEmployeeFiller() {
        ServerState state = ServerState.OK;
        entity.setId(34l);
        entity.setServer_name("localhost");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(state);
        entity.setResponse(state.toString());
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);


        assertEquals("failure - id should be same", (Object)34l,  entity.getId());
        assertEquals("failure - server_name should be same", "localhost", entity.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.0", entity.getAddress());
        assertEquals("failure - port should be same", (Object)8080,  entity.getPort());
        assertEquals("failure - URL should be same", state,  entity.getState());
        assertEquals("failure - URL should be same", state.toString(),  entity.getResponse());
        assertEquals("failure - URL should be same", "http://localhost/",  entity.getUrl());
        assertEquals("failure - created should be same", timestamp, entity.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity.getLastCheck());
        assertEquals("failure - isActive should be same", (Object)1, entity.getActive());

    }
}
