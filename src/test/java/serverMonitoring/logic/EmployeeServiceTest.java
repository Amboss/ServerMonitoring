package serverMonitoring.logic;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.logic.service.serviceImpl.EmployeeServiceImpl;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class EmployeeServiceTest {

    private ShaPasswordEncoder passwordEncoder;
    private Date date;
    private Timestamp timestamp;

    @Autowired
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeClass
    public void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
        date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Retrieving employee with login "user" and changing password to "54321",
     * repeating retrieve again,"user" have to contain a new password
     * Testing retrieved Employee entity.getPassword() value.
     */
    @Test
    @Transactional
    public void testChangePassword() {
        EmployeeEntity entity = new EmployeeEntity();
        try {
            entity.setLogin("user");
            entity = employeeService.getEmployeeByLogin(entity);
            employeeService.changePassword(entity, "54321");
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeEntity entity2 = new EmployeeEntity();
        String testPass = null;
        try {
            entity2.setLogin("user");
            entity2 = employeeService.getEmployeeByLogin(entity);
            testPass = passwordEncoder.encodePassword("54321",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("failure - password must be same", testPass, entity2.getLogin());
        //04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb  - "user"
        //20f3765880a5c269b747e1e906054a4b4a3a991259f1e16b5dde4742cec2319a  - "54321"
    }

    /**
     * Retrieving server with id "9"
     * Testing retrieved server entity.getState value.
     */
    @Test
    @Transactional
    public void testGetServerState() {
        ServerEntity entity = new ServerEntity();
        ServerState state = null;
        try {
            entity.setId(9l);
            state = employeeService.getServerState(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("failure - state must be same", ServerState.valueOf("OK"), state);
    }

    /**
     *  Retrieving server details with id "9"
     *  Testing if retrieved value is not empty.
     */
    @Test
    @Transactional
    public void testGetDetails() {
        ServerEntity entity = new ServerEntity();
        try {
            entity.setId(9l);
            entity = employeeService.getServerDetails(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull("failure - getServerDetails must be same NotNull",entity);
    }
}
