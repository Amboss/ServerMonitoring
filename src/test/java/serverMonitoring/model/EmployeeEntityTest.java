package serverMonitoring.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the {@link EmployeeEntity} class.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeEntityTest {


    Date date;
    ShaPasswordEncoder passwordEncoder;
    Timestamp timestamp;

    @BeforeClass
    public void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
        date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * Testing Employee entity setters/getters functionality
     */
    @Test
    public void testSetEmployeeFiller() {
        EmployeeEntity entity = new EmployeeEntity();
        String pass = passwordEncoder.encodePassword("12345", null);
        try {
            entity.setId(2l);
            entity.setEmployee_name("Default_FirstName &LastName");
            entity.setLogin("Default_login");
            entity.setPassword(pass);
            entity.setEmail("default_email@mail.com");
            entity.setCreated(timestamp);
            entity.setLastLogin(timestamp);
            entity.setActive(1);
            entity.setAdmin(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("failure - id should be same", 1l, (Object) entity.getId());
        assertEquals("failure - entity_name should be same", "Default_FirstName &LastName",
                entity.getEmployee_name());
        assertEquals("failure - login should be same", "Default_login", entity.getLogin());
        assertEquals("failure - password should be same", pass, entity.getPassword());
        assertEquals("failure - password should be same", "default_email@mail.com", entity.getEmail());
        assertEquals("failure - created should be same", timestamp, entity.getCreated());
        assertEquals("failure - lastLogin should be same", timestamp, entity.getLastLogin());
        assertEquals("failure - isActive should be same", (Object) 1, entity.getActive());
        assertEquals("failure - isAdmin should be same", (Object) 0, entity.getAdmin());
    }
}
