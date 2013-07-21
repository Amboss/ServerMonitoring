package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serverMonitoring.controller.AbstractCommonController;
import serverMonitoring.logic.service.AdminService;

/**
 * Abstract controller for Admin pages.
 *  - sets AdminService
 */
@Controller
public abstract class AbstractAdminController extends AbstractCommonController {

    protected static Logger logger = Logger.getLogger(AbstractAdminController.class);

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }



}
