package serverMonitoring.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link ServerEntity} class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ServerEntityTest extends AbstractJUnit4SpringContextTests {

    private static Date date;
    private static ShaPasswordEncoder passwordEncoder;
    private static Timestamp timestamp;

    @BeforeClass
    public static void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
        date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Testing Server entity setters/getters functionality
     */
    @Test
    public void testSetEmployeeFiller() {
        ServerEntity entity = new ServerEntity();
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

        assertEquals("failure - id should be same", (Object) 34l, entity.getId());
        assertEquals("failure - server_name should be same", "localhost", entity.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.0", entity.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity.getPort());
        assertEquals("failure - state should be same", state, entity.getState());
        assertEquals("failure - Response should be same", state.toString(), entity.getResponse());
        assertEquals("failure - URL should be same", "http://localhost/", entity.getUrl());
        assertEquals("failure - created should be same", timestamp, entity.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity.getLastCheck());
        assertEquals("failure - isActive should be same", (Object) 1, entity.getActive());
    }
}
