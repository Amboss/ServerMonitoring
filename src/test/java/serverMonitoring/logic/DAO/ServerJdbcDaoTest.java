package serverMonitoring.logic.DAO;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.model.ServerEntity;

import java.util.List;

/**
 * JUnit test for the {@link ServerJdbcDaoTest} class.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ServerJdbcDaoTest extends AbstractJUnit4SpringContextTests {

    protected static Logger employeeLogger = Logger.getLogger("EmployeeServiceImpl");
    private static ShaPasswordEncoder passwordEncoder;
    private ServerDao serverDao;

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    /**
     *
     * @param entity
     */
    @Test
    public void testAdd(ServerDao entity) {

    }

    /**
     *
     * @param entity
     */
    @Test
    public void testAddGroup(final List<ServerEntity> entity) {

    }

    /**
     *
     * @param entity
     */
    @Test
    public void testUpdate(ServerEntity entity) {

    }

    /**
     *
     * @param entity
     */
    @Test
    public void testDelete(Long entity_id) {

    }

    /**
     *
     * @param entity
     */
    @Test
    public ServerEntity testFindById(Long entity_id) {

        return null;
    }

    /**
     *
     * @param entity
     */
    @Test
    public List<ServerEntity> testFindAll() {

        return null;
    }
}
