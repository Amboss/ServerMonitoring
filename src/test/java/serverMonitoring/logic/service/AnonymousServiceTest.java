package serverMonitoring.logic.service;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.impl.AnonymousServiceImpl;
import serverMonitoring.model.EmployeeEntity;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link AnonymousServiceImpl} class.
 * Testing basic capabilities of user with ROLE_Anonymous access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class AnonymousServiceTest extends AbstractJUnit4SpringContextTests {

    private static ShaPasswordEncoder passwordEncoder;
    private static Timestamp timestamp;
    private AnonymousService anonymousService;
    private EmployeeService employeeService;
    private AdminService adminService;

    @Autowired
    public void setAnonymousService(AnonymousService anonymousService) {
        this.anonymousService = anonymousService;
    }

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
     * termination of Employee
     */
    @After
    public void termination() {
        EmployeeEntity entity = employeeService.getEmployeeByLogin("testUser");
        adminService.deleteEmployee(entity.getId());
    }

    /**
     * Testing selection of EmployeeEntity from Data Base by ID
     */
    @Test
    public void testFindById() {
        //registration of new user
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

        // selection and assert of test user
        EmployeeEntity entity2 = anonymousService.getEmployeeByEmail("test_email@mail.com");
        assertNotNull("failure - Employee entity2 must not be null", entity2);
        assertEquals("failure - entity_name should be same", "Service_Test_Register", entity2.getEmployee_name());
        assertEquals("failure - login should be same", "testUser", entity2.getLogin());
        assertEquals("failure - password should be same", pass, entity2.getPassword());
        assertEquals("failure - password should be same", "test_email@mail.com", entity2.getEmail());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());
        assertEquals("failure - isAdmin should be same", (Object) 0, entity2.getAdmin());

    }

    /**
     * Retrieving employee with Email "test_email@mail.com" and changing password to "54321",
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
        anonymousService.updateEmployeePassword(entity, "54321");

        EmployeeEntity entity2 = anonymousService.getEmployeeByEmail("test_email@mail.com");
        assertNotNull(entity2);
        String testPass = passwordEncoder.encodePassword("54321", null);
        // asserting password row
        assertEquals("failure - password must be same", testPass, entity2.getPassword());
    }
}
