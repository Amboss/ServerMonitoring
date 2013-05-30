package serverMonitoring.logic.DAO;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.serverStateEnum.ServerState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link ServerJdbcDaoTest} class.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ServerJdbcDaoTest extends AbstractJUnit4SpringContextTests {

    // protected static Logger employeeLogger = Logger.getLogger("EmployeeServiceImpl");
    private static Timestamp timestamp;
    private ServerDao serverDao;

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @BeforeClass
    public static void initiate() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * termination of EmployeeEntity from Data Base
     */
    @After
    public void testDelete() {
        ServerEntity entity = serverDao.findByServerName("Test_Server");
        serverDao.delete(entity.getId());
    }

    /**
     * Testing registration of new EmployeeEntity to Data Base
     */
    @Test
    public void testAdd() {
        // creating new server entity
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.FAIL;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        serverDao.add(entity);

        // selecting by login & asserting
        ServerEntity entity2 = serverDao.findByServerName("Test_Server");
        assertNotNull("failure - Server entity2 must not be null", entity2);
        assertEquals("failure - address should be same", "255.255.255.0", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity2.getPort());
        assertEquals("failure - url should be same", "http://localhost/", entity2.getUrl());
        assertEquals("failure - state should be same", serverState, entity2.getState());
        assertEquals("failure - state should be same", serverStateString, entity2.getResponse());
        assertEquals("failure - isActive should be same", (Object) 1, entity2.getActive());
    }

    /**
     * Testing registration group of new ServerEntity to Data Base
     */
    @Test
    public void testAddGroup() {
        List<ServerEntity> entityList = new ArrayList<>();
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.WARN;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        entityList.add(entity);
        serverDao.addGroup(entityList);

        // testing selection off all entities
        List<ServerEntity> entityList2 = serverDao.findAll();
        assertNotNull(entityList2);
    }


    /**
     * Testing selection of ServerEntity from Data Base by ID
     */
    @Test
    public void testFindById() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.WARN;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        serverDao.add(entity);

        ServerEntity entity2 = serverDao.findByServerName("Test_Server");
        assertNotNull("failure - Employee entity2 must not be null", entity2);

        // selecting by ID
        Long id = entity2.getId();
        ServerEntity entity3 = serverDao.findById(id);

        // asserting
        assertNotNull("failure - Server entity must not be null", entity3);
        assertEquals("failure - address should be same", "255.255.255.0", entity3.getAddress());
        assertEquals("failure - port should be same", (Object) 8080, entity3.getPort());
        assertEquals("failure - url should be same", "http://localhost/", entity3.getUrl());
        assertEquals("failure - state should be same", serverState, entity3.getState());
        assertEquals("failure - state should be same", serverStateString, entity3.getResponse());
        assertEquals("failure - isActive should be same", (Object) 1, entity3.getActive());
    }

    /**
     * Testing update of existing ServerEntity in Data Base
     */
    @Test
    public void testUpdate() {
        ServerEntity entity = new ServerEntity();
        ServerState serverState = ServerState.OK;
        String serverStateString = ServerState.getStringFromEnum(serverState);
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.0");
        entity.setPort(8080);
        entity.setUrl("http://localhost/");
        entity.setState(serverState);
        entity.setResponse(serverStateString);
        entity.setCreated(timestamp);
        entity.setLastCheck(timestamp);
        entity.setActive(1);
        serverDao.add(entity);

        // selecting existent entity
        entity.setServer_name("Test_Server");
        entity.setAddress("255.255.255.10");
        entity.setPort(9090);
        entity.setActive(0);
        serverDao.update(entity);

        // selection and asserting
        ServerEntity entity2 = serverDao.findByServerName("Test_Server");
        assertNotNull("failure - Employee entity2 must not be null", entity2);

        // asserting
        assertNotNull("failure - Server entity must not be null", entity2);
        assertEquals("failure - address should be same", "255.255.255.10", entity2.getAddress());
        assertEquals("failure - port should be same", (Object) 9090, entity2.getPort());
        assertEquals("failure - isActive should be same", (Object) 0, entity2.getActive());
    }
}
