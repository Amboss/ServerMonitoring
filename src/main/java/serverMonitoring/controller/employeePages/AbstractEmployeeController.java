package serverMonitoring.controller.employeePages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serverMonitoring.controller.AbstractCommonController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;

/**
 * Abstract controller for Employee pages.
 *  - sets EmployeeService
 */
@Controller
public abstract class AbstractEmployeeController extends AbstractCommonController {

    protected static Logger logger = Logger.getLogger(AbstractEmployeeController.class);

    private EmployeeService employeeService;

    private AdminService adminService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * @return current Entity login
     */
    public Long getUserId() {
        EmployeeEntity entity = employeeService.getEmployeeByLogin(getUserName());
        return entity.getId();
    }
}
