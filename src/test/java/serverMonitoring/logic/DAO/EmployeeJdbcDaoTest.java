package serverMonitoring.logic.DAO;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit test for the {@link EmployeeJdbcDaoTest} class.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class EmployeeJdbcDaoTest extends AbstractJUnit4SpringContextTests {


}
