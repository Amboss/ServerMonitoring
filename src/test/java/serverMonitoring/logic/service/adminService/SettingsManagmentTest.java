package serverMonitoring.logic.service.adminService;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ftl.SystemSettingsModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class SettingsManagmentTest extends AbstractJUnit4SpringContextTests {

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

    /**
     * termination of EmployeeEntity from Data Base
     */
    @After
    public void testDelete() {
        adminService.deleteSettings("newSetting");
    }

    /**
     * Testing creation of settings in Data Base
     */
    @Test
    public void testAddSettings() {

        // updating settings
        SystemSettingsModel settingsModel = new SystemSettingsModel();
        settingsModel.setSettings_name("newSetting");
        settingsModel.setServerScanInterval(10000);
        settingsModel.setTimeoutOfRespond(20000);
        settingsModel.setPageReloadTime(5000);
        settingsModel.setSmtpServerHost("http://address.com");
        settingsModel.setSmtpServerPort(80);
        adminService.addSettings(settingsModel);

        // selecting settings
        SystemSettingsModel settingsModel2 = employeeService.getSettingsByName("newSetting");

        // assertion
        assertNotNull("failure - settingsModel2 must not be null", settingsModel2);
        assertEquals("failure - Settings_name should be same", "newSetting", settingsModel2.getSettings_name());
        assertEquals("failure - ServerScanInterval should be same", (Object) 10000, settingsModel2.getServerScanInterval());
        assertEquals("failure - TimeoutOfRespound should be same", (Object) 20000, settingsModel2.getTimeoutOfRespond());
        assertEquals("failure - PageReloadTime should be same", (Object) 5000, settingsModel2.getPageReloadTime());
        assertEquals("failure - SmtpServerAdress should be same", "http://address.com", settingsModel2.getSmtpServerHost());
        assertEquals("failure - SmtpServerPort should be same", (Object) 80, settingsModel2.getSmtpServerPort());
    }

    /**
     * Testing renovation of settings in Data Base
     */
    @Test
    public void testUpdateSettings() {

        // updating settings
        SystemSettingsModel settingsModel = new SystemSettingsModel();
        settingsModel.setSettings_name("newSetting");
        settingsModel.setServerScanInterval(10000);
        settingsModel.setTimeoutOfRespond(20000);
        settingsModel.setPageReloadTime(5000);
        settingsModel.setSmtpServerHost("http://address.com");
        settingsModel.setSmtpServerPort(80);
        adminService.addSettings(settingsModel);

        // updating
        settingsModel.setServerScanInterval(40000);
        settingsModel.setTimeoutOfRespond(40000);
        settingsModel.setPageReloadTime(4000);
        settingsModel.setSmtpServerHost("http://address4444.com");
        settingsModel.setSmtpServerPort(40);
        adminService.updateSettings(settingsModel);

        // selecting settings
        SystemSettingsModel settingsModel2 = employeeService.getSettingsByName("newSetting");

        // assertion
        assertNotNull("failure - settingsModel2 must not be null", settingsModel2);
        assertEquals("failure - Settings_name should be same", "newSetting", settingsModel2.getSettings_name());
        assertEquals("failure - ServerScanInterval should be same", (Object) 40000, settingsModel2.getServerScanInterval());
        assertEquals("failure - TimeoutOfRespound should be same", (Object) 40000, settingsModel2.getTimeoutOfRespond());
        assertEquals("failure - PageReloadTime should be same", (Object) 4000, settingsModel2.getPageReloadTime());
        assertEquals("failure - SmtpServerAdress should be same", "http://address4444.com", settingsModel2.getSmtpServerHost());
        assertEquals("failure - SmtpServerPort should be same", (Object) 40, settingsModel2.getSmtpServerPort());
    }
}
