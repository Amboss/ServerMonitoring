package serverMonitoring.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.serviceImpl.AdminServiceImpl;
import serverMonitoring.model.EmployeeEntity;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link AdminServiceImpl} class.
 * Testing basic capabilities of user with ROLE_ADMIN access
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class AdminServiceTest {

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    @Autowired
    AdminService adminService = new AdminServiceImpl();
    @Autowired

    /**
     * Registration of new Employee
     * testing all raw from Employee entity
     */
    @Before
    public void testRegisterEmployee() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
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
        assertEquals("failure - entity_name should be same", "Test_Employee_Name", entity2.getEmployee_name());
        assertEquals("failure - login should be same", "testUser", entity2.getLogin());
        assertEquals("failure - password should be same", pass, entity2.getPassword());
        assertEquals("failure - password should be same", "test_email@mail.com", entity2.getEmail());
        assertEquals("failure - created should be same", timestamp, entity2.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity2.getLastLogin());
        assertEquals("failure - isActive should be same", (Object) 1,  entity2.getActive());
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

        try {
            adminService.getEmployeeByLogin(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        entity.setEmail("new_test_email@mail.com");
        entity.setActive(0);
        try {
            adminService.updateEmployee(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("failure - password should be same", "test_email@mail.com", entity.getEmail());
        assertEquals("failure - isActive should be same", (Object)0,  entity.getActive());
    }

    /**
     * delete Employee
     */
    @Test
    public void testDeleteEmployee() {


    }

    /**
     * register the new Server
     */
    @Test
    public void testRegisterServer() {

    }

    /**
     * update Employee Info
     */
    @Test
    public void testUpdateServer() {

    }

    /**
     * delete Server
     */
    @Test
    public void testdeleteServer() {

    }

    /**
     * retrieve all Servers
     */
    @Test
    public void testGetAllServers() {

    }

    /**
     * retrieve all Employees
     */
    @Test
    public void testGetAllEmployee() {

    }

}

