package serverMonitoring.logic;

import junitx.framework.ListAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.serviceImpl.AdminServiceImpl;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * JUnit test for the {@link AdminServiceImpl} class.
 * Testing basic capabilities of user with ROLE_ADMIN access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class AdminServiceTest {

    private ShaPasswordEncoder passwordEncoder;
    private Date date;
    private Timestamp timestamp;

    @Autowired
    AdminService adminService = new AdminServiceImpl();

    @BeforeClass
    public void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
        date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Registration of new Employee
     * testing all raw from Employee entity
     */
    @Before
    public void testRegisterEmployee() {
        EmployeeEntity entity = new EmployeeEntity();
        EmployeeEntity entity2 = new EmployeeEntity();
        String pass = passwordEncoder.encodePassword("testpass", null);

        entity.setEmployee_name("Test_Employee_Name");
        entity.setLogin("testUser");
        entity.setPassword(pass);
        entity.setEmail("test_email@mail.com");
        entity.setCreated(timestamp);
        entity.setLastLogin(timestamp);
        entity.setActive(1);
        entity.setAdmin(0);

        try {
            adminService.registerEmployee(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            entity2 = adminService.getEmployeeByLogin(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull("failure - Employee entity2 must not be null", entity2);

        assertEquals("failure - entity_name should be same", "Test_Employee_Name", entity2.getEmployee_name());
        assertEquals("failure - login should be same", "testUser", entity2.getLogin());
        assertEquals("failure - password should be same", pass, entity2.getPassword());
        assertEquals("failure - password should be same", "test_email@mail.com", entity2.getEmail());
        assertEquals("failure - created should be same", timestamp, entity2.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity2.getLastLogin());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());
        assertEquals("failure - isAdmin should be same", (Object) 0, entity2.getAdmin());

    }

    /**
     * Retrieving employee with login "testUser" and changing email and active rows
     * updating entity and repeating retrieve again, "testUser" have to contain a new email and active rows
     * Testing retrieved Employee entity.getEmail() and entity.getActive() value.
     */
    @Test
    public void testUpdateEmployee() {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setLogin("testUser");
        entity.setEmail("new_test_email@mail.com");
        entity.setActive(0);

        try {
            adminService.updateEmployee(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // retrieving entity
        try {
            entity.setLogin("testUser");
            entity = adminService.getEmployeeByLogin(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("failure - password should be same", "test_email@mail.com", entity.getEmail());
        assertEquals("failure - isActive should be same", (Object) 0, entity.getActive());
    }

    /**
     * retrieve all Employees
     * Testing existence of testUser & testUser2
     */
    @Test
    public void testGetAllEmployee() {
        EmployeeEntity entity = new EmployeeEntity();
        EmployeeEntity entity2 = new EmployeeEntity();
        String pass = passwordEncoder.encodePassword("testpass2", null);
        List<EmployeeEntity> reality = new ArrayList<>();
        List<EmployeeEntity> expected = new ArrayList<>();

        entity2.setEmployee_name("Test_Employee_Name2");
        entity2.setLogin("testUser2");
        entity2.setPassword(pass);
        entity2.setEmail("test2_email@mail.com");
        entity2.setCreated(timestamp);
        entity2.setLastLogin(timestamp);
        entity2.setActive(1);
        entity2.setAdmin(0);

        // setting testUser for expected list
        entity.setEmployee_name("Test_Employee_Name");
        entity.setLogin("testUser");
        entity.setPassword(pass);
        entity.setEmail("test_email@mail.com");
        entity.setCreated(timestamp);
        entity.setLastLogin(timestamp);
        entity.setActive(1);
        entity.setAdmin(0);

        // updating employee to set same timestamp
        try {
            adminService.updateEmployee(entity);
            adminService.registerEmployee(entity2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            reality = adminService.getAllEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // adding expected List of Employee entity objects
        expected.add(entity);
        expected.add(entity2);

        ListAssert.assertEquals("value of list must be the same", expected, reality);

    }

    /**
     * delete Employee
     * testing for absence of "testUser" & "testUser2"
     */
    @After
    public void testDeleteEmployee() {
        EmployeeEntity entity = new EmployeeEntity();
        EmployeeEntity entity2 = new EmployeeEntity();

        // deleting entity
        try {

            entity.setLogin("testUser");
            entity = adminService.getEmployeeByLogin(entity);
            Long id = entity.getId();
            adminService.deleteEmployee(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // deleting entity2
        try {
            entity.setLogin("testUser2");
            entity = adminService.getEmployeeByLogin(entity);
            Long id = entity.getId();
            adminService.deleteEmployee(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //retrieving and asserting for null
        try {
            entity.setLogin("testUser");
            entity = adminService.getEmployeeByLogin(entity);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertNull("the configuration is not empty", entity);

        try {
            entity2.setLogin("testUser");
            entity2 = adminService.getEmployeeByLogin(entity2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertNull("the configuration is not empty", entity2);
    }

    /**
     * register the new Server
     * testing all raw from Server entity
     */
    @Before
    public void testRegisterServer() {
        ServerEntity entity = new ServerEntity();
        ServerEntity entity2 = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getString(serverState);

        entity.setId(2l);
        entity.setServer_name("Test_Server1");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);

        try {
            adminService.registerServer(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            entity.setId(2l);
            entity2 = adminService.getServerDetails(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull("failure - Server entity2 must not be null", entity2);

        assertEquals("failure - server_name should be same", "Test_Server1", entity2.getServer_name());
        assertEquals("failure - address should be same", "255.255.255.0", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
        assertEquals("failure - created should be same", timestamp, entity2.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity2.getLastCheck());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());

    }

    /**
     * update ServerEntity Info
     * testing Server entity serverState, url & port
     */
    @Test
    public void testUpdateServer() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.WARN;

        entity.setId(2l);
        entity.setUrl("http://localhost/test_change");
        entity.setState(serverState);
        entity.setPort(9999);

        try {
            adminService.updateServer(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // retrieving entity
        try {
            entity.setId(2l);
            adminService.getServerDetails(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        ServerEntity entity = new ServerEntity();
        ServerEntity entity2 = new ServerEntity();
        List<ServerEntity> reality = new ArrayList<>();
        List<ServerEntity> expected = new ArrayList<>();
        ServerState serverState = ServerState.OK;
        String serverStateString = ServerState.getString(serverState);

        entity.setId(2l);
        entity.setServer_name("Test_Server1");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/test1");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);

        // setting testUser for expected list
        entity2.setId(3l);
        entity2.setServer_name("Test_Server2");
        entity2.setAddress("255.255.255.1");
        entity2.setPort(8080);
        entity2.setUrl("http://localhost/test2");
        entity2.setState(serverState);
        entity2.setResponse(serverStateString);
        entity2.setCreated(timestamp);
        entity2.setLastCheck(timestamp);
        entity2.setActive(1);

        // updating employee to set same timestamp
        try {
            adminService.updateServer(entity);
            adminService.registerServer(entity2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            reality = adminService.getAllServers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // adding to expected List of Servers entity objects
        expected.add(entity);
        expected.add(entity2);

        ListAssert.assertEquals("value of list must be the same", expected, reality);
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
        try {
            entity.setId(2l);
            entity = adminService.getServerDetails(entity);
            Long id = entity.getId();
            adminService.deleteServer(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // deleting entity2
        try {
            entity2.setId(3l);
            entity2 = adminService.getServerDetails(entity2);
            Long id = entity2.getId();
            adminService.deleteServer(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //retrieving and asserting for null
        try {
            entity.setId(2l);
            entity = adminService.getServerDetails(entity);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertNull("the configuration is not empty", entity);

        try {
            entity2.setId(3l);
            entity2 = adminService.getServerDetails(entity2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertNull("the configuration is not empty", entity2);
    }
}

