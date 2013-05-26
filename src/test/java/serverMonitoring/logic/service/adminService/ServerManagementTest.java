package serverMonitoring.logic.service.adminService;

import org.junit.After;
import org.junit.Before;
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

import static org.junit.Assert.*;

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
     * register the new Server
     * testing all raw from Server entity
     */
    @Before
    public void testRegisterServer() {
        // creating new server entity
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setId(2l);
        entity.setServer_name("Test_Server_Register");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        adminService.registerServer(entity);

        // selecting and asserting server entity
        ServerEntity entity2 = employeeService.getServerDetails(entity);
        assertNotNull("failure - Server entity2 must not be null", entity2);
        assertEquals("failure - server_name should be same", "Test_Server_Register", entity2.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.0", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
//        assertEquals("failure - created should be same", timestamp, entity2.getCreated());
//        assertEquals("failure - lastLogin should be same", timestamp, entity2.getLastCheck());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());
    }

    /**
     * update ServerEntity Info
     * testing Server entity serverState, url & port
     */
    @Test
    public void testUpdateServer() {
        // updating existent server entity
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.WARN;
        entity.setId(2l);
        entity.setServer_name("Test_Server_Update");
        entity.setUrl("http://localhost/test_change");
        entity.setState(serverState);
        entity.setPort(9999);
        adminService.updateServer(entity);

        // selecting and asserting server entity
        entity.setId(2l);
        entity = employeeService.getServerDetails(entity);
        assertNotNull(entity);
        assertEquals("failure - password should be same", "http://localhost/test_change", entity.getUrl());
        assertEquals("failure - state should be same", serverState, entity.getState());
        assertEquals("failure - isActive should be same", (Object) 9999, entity.getPort());
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
        ServerEntity entity2 = new ServerEntity();
        entity2.setId(3l);
        entity2.setServer_name("Test_Server_GetAll_2");
        entity2.setAddress("255.255.255.1");
        entity2.setPort(8080);
        entity2.setUrl("http://localhost/test2");
        entity2.setState(serverState);
        entity2.setResponse(serverStateString);
        entity2.setCreated(timestamp);
        entity2.setLastCheck(timestamp);
        entity2.setActive(1);
        adminService.registerServer(entity2);

        List<ServerEntity> reality = adminService.getAllServers();
        assertNotNull(reality);
    }

    /**
     * delete Server
     * testing for absence of "Test_Server1" & "Test_Server2"
     */
    @After
    public void testDeleteServer() {
        ServerEntity entity = new ServerEntity();
        ServerEntity entity2 = new ServerEntity();

        // deleting entity
        entity.setId(2l);
        entity = employeeService.getServerDetails(entity);
        Long id = entity.getId();
        adminService.deleteServer(id);

        // deleting entity2
        entity2.setId(3l);
        entity2 = employeeService.getServerDetails(entity2);
        Long id2 = entity2.getId();
        adminService.deleteServer(id2);

        //retrieving and asserting for null Test_Server1
        entity.setId(2l);
        entity = employeeService.getServerDetails(entity);
        assertNull("the configuration is not empty", entity);

        //retrieving and asserting for null Test_Server2
        entity2.setId(3l);
        entity2 = employeeService.getServerDetails(entity2);
        assertNull("the configuration is not empty", entity2);
    }
}

