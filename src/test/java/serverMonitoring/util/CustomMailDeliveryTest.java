package serverMonitoring.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.util.mail.CustomMailDelivery;

import javax.mail.SendFailedException;

import static org.junit.Assert.assertEquals;

/**
 * Custom Mail Delivery Test
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class CustomMailDeliveryTest extends AbstractJUnit4SpringContextTests {

    private EmployeeService employeeService;

    private CustomMailDelivery customMailDelivery;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setCustomMailDelivery(CustomMailDelivery customMailDelivery) {
        this.customMailDelivery = customMailDelivery;
    }

    /**
     * Testing sen mail.
     */
    @Test
    public void testSendMail() {
        try {
            customMailDelivery.sendMail("huskyserge@gmail.com",
                    "husky_serge@ukr.net",
                    "This is test mail!",
                    "Congratulations, you received test mail!");

        } catch (SendFailedException e) {
            assertEquals("failure - error must be null", null, e);
        }
    }
}
