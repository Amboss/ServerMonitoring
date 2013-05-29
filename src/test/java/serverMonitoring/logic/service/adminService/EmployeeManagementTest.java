package serverMonitoring.logic.service.adminService;

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
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test for the {@link serverMonitoring.logic.service.impl.AdminServiceImpl} class.
 * Testing Employee Management capabilities of user with ROLE_ADMIN access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class EmployeeManagementTest extends AbstractJUnit4SpringContextTests {
    private static ShaPasswordEncoder passwordEncoder;
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
        passwordEncoder = new ShaPasswordEncoder(256);
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Registration of new Employee
     * testing all raw from Employee entity
     */
    @Test
    public void testRegisterEmployee() {
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
        EmployeeEntity entity2 = employeeService.getEmployeeByLogin(entity);
        assertNotNull("failure - Employee entity2 must not be null", entity2);
        assertEquals("failure - entity_name should be same", "Service_Test_Register", entity2.getEmployee_name());
        assertEquals("failure - login should be same", "testUser", entity2.getLogin());
        assertEquals("failure - password should be same", pass, entity2.getPassword());
        assertEquals("failure - password should be same", "test_email@mail.com", entity2.getEmail());
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

        // updating employee
        entity.setLogin("testUser");
        entity.setEmail("new_test_email@mail.com");
        entity.setEmployee_name("Service_Test_Update");
        entity.setActive(0);
        adminService.updateEmployee(entity);

        // selection and assert of test user
        EmployeeEntity entity2 = employeeService.getEmployeeByLogin(entity);
        assertNotNull(entity2);
        assertEquals("failure - password should be same", "new_test_email@mail.com", entity2.getEmail());
        assertEquals("failure - name should be same", "Service_Test_Update", entity2.getEmployee_name());
        assertEquals("failure - isActive should be same", (Object) 0, entity2.getActive());
    }

    /**
     * retrieve all Employees
     * Testing existence of testUser & testUser2
     */
    @Test
    public void testGetAllEmployee() {
        EmployeeEntity entity = new EmployeeEntity();
        String pass = passwordEncoder.encodePassword("test", null);
        // registration of new user
        entity.setEmployee_name("Service_Test_GetAll");
        entity.setLogin("testUser");
        entity.setPassword(pass);
        entity.setEmail("test2_email@mail.com");
        entity.setCreated(timestamp);
        entity.setLastLogin(timestamp);
        entity.setActive(1);
        entity.setAdmin(0);
        adminService.registerEmployee(entity);

        // creating expected and reality Lists for comparing assert
        List<EmployeeEntity> reality = adminService.getAllEmployee();
        assertNotNull(reality);
    }

    /**
     * delete Employee
     */
    @After
    public void Delete() {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setLogin("testUser");
        entity = employeeService.getEmployeeByLogin(entity);
        adminService.deleteEmployee(entity.getId());
        //assertNull("the testUser2 is not empty", entity);
    }
}
