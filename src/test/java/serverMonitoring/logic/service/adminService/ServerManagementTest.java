package serverMonitoring.logic.service.adminService;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.logic.service.impl.AdminServiceImpl;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link AdminServiceImpl} class.
 * Testing Server Management capabilities of user with ROLE_ADMIN access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ServerManagementTest extends AbstractJUnit4SpringContextTests {

    private static Date date;
    private static Timestamp timestamp;
    private AdminService adminService;
    private EmployeeService employeeService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @BeforeClass
    public static void initiate() {
        date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * delete Server
     */
    @After
    public void testDeleteServer() {
        ServerEntity entity = new ServerEntity();
        entity.setServer_name("Test_Server");
        ServerEntity entity2 = employeeService.getServerDetails(entity);
        adminService.deleteServer(entity2.getId());
        //assertNull("the Test_Server is not empty", entity);
    }

    /**
     * register the new Server
     * testing all raw from Server entity
     */
    @Test
    public void testRegisterServer() {
        // creating new server entity
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/register");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        adminService.registerServer(entity);

        // selecting and asserting server entity
        ServerEntity entity2 = employeeService.getServerDetails(entity);
        assertNotNull("failure - Server entity2 must not be null", entity2);
        assertEquals("failure - server_name should be same", "Test_Server", entity2.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.0", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/register", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());
    }

    /**
     * update ServerEntity Info
     * testing Server entity serverState, url & port
     */
    @Test
    public void testUpdateServer() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/register");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        adminService.registerServer(entity);

        // updating existent entity
        ServerState serverState2 = ServerState.WARN;
        entity.setServer_name("Test_Server");
        entity.setUrl("http://localhost/update");
        entity.setState(serverState2);
        entity.setPort(9999);
        adminService.updateServer(entity);

        // selecting and asserting server entity
        entity.setServer_name("Test_Server");
        ServerEntity entity2 = employeeService.getServerDetails(entity);
        assertNotNull(entity);
        assertEquals("failure - password should be same", "http://localhost/update", entity2.getUrl());
        assertEquals("failure - state should be same", serverState2, entity2.getState());
        assertEquals("failure - isActive should be same", (Object) 9999, entity2.getPort());
    }

    /**
     * retrieve all Servers
     * Testing existence of Test_Server1 & Test_Server2
     */
    @Test
    public void testGetAllServers() {
        ServerState serverState = ServerState.OK;
        String serverStateString = ServerState.getStringFromEnum(serverState);

        // creating new server entity
        ServerEntity entity = new ServerEntity();
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.1");
        entity.setPort(8080);
        entity.setUrl("http://localhost/GetAll");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        adminService.registerServer(entity);

        List<ServerEntity> reality = adminService.getAllServers();
        assertNotNull(reality);
    }
}

