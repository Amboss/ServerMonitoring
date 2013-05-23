package serverMonitoring.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import serverMonitoring.model.serverStateEnum.ServerState;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link ServerState} enum.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ServerStateTest extends AbstractJUnit4SpringContextTests {

    private ServerEntity serverEntity;

    @Before
    public void initiate() {
        serverEntity = new ServerEntity();
    }

    /**
     * Testing conversion function from Enum object to exact String
     */
    @Test
    public void testGetEnumFromString() {
        ServerState state = ServerState.getEnumFromString("OK");
        assertEquals("failure - state must contain OK", ServerState.OK, state);
    }

    /**
     * Testing conversion function from String object to exact Enum
     */
    @Test
    public void testGetStringFromEnum() {

        String str = ServerState.getStringFromEnum(ServerState.WARN);
        assertEquals("failure - state must contain WARN", "WARN", str);
    }
}
