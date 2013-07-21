package serverMonitoring.logic.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.impl.EmployeeServiceImpl;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link EmployeeServiceImpl} class.
 * Testing basic capabilities of user with ROLE_USER access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class EmployeeServiceTest extends AbstractJUnit4SpringContextTests {

    private static ShaPasswordEncoder passwordEncoder;
    private static Timestamp timestamp;
    private EmployeeService employeeService;
    private AdminService adminService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @BeforeClass
    public static void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Retrieving employee with login "testUser" and changing password to "54321",
     * Testing retrieved Employee entity.getPassword() value.
     */
    @Test
    public void testPasswordUpdate() {
        EmployeeEntity entity = new EmployeeEntity();
        String pass = passwordEncoder.encodePassword("testpass", null);
        entity.setEmployee_name("Service_Test_Register");
        entity.setLogin("testUser");
        entity.setPassword(pass);
        entity.setEmail("test_email@mail.com");
        entity.setCreated(timestamp);
        entity.setLastLogin(timestamp);
        entity.setActive(1);
        entity.setAdmin(0);
        adminService.registerEmployee(entity);

        // updating entity password
        entity.setLogin("testUser");
        employeeService.updateEmployeePassword(entity, "54321");

        EmployeeEntity entity2 = employeeService.getEmployeeByLogin("testUser");
        assertNotNull(entity2);
        String testPass = passwordEncoder.encodePassword("54321", null);
        // asserting password row
        assertEquals("failure - password must be same", testPass, entity2.getPassword());
        // eliminating test entity
        adminService.deleteEmployee(entity.getId());
    }

    /**
     * Retrieving server with id "9"
     * Testing retrieved server entity.getState value.
     */
    @Test
    public void testGetServerState() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.OK;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        // registering new server
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.6");
        entity.setPort(8080);
        entity.setUrl("http://localhost/register");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        adminService.registerServer(entity);

        ServerEntity entity2 = employeeService.getServerByName("Test_Server");
        assertNotNull("failure - Server entity must not be null", entity2);
        assertEquals("failure - server_name should be same", "Test_Server", entity2.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.6", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/register", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());

        // eliminating test entity
        adminService.deleteServer(entity.getId());
    }

    /**
     * Retrieving server details with id "9"
     * Testing if retrieved value is not empty.
     */
    @Test
    public void testGetDetails() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.7");
        entity.setPort(8090);
        entity.setUrl("http://localhost/test");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(0);
        adminService.registerServer(entity);

        // selecting and asserting server entity
        ServerEntity entity2 = employeeService.getServerByName(entity.getServer_name());
        assertNotNull("failure - Server entity must not be null", entity2);
        assertEquals("failure - server_name should be same", "Test_Server", entity2.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.7", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8090, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/test", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
        assertEquals("failure - isActive should be same", (Object) 0, entity2.getActive());

        // eliminating test entity
        adminService.deleteServer(entity2.getId());
    }


}
