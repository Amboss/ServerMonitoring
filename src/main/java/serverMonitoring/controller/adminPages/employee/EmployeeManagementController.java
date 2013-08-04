package serverMonitoring.controller.adminPages.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.util.common.CustomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles and retrieves the ROLE_ADMIN admin/employee_management/employee_manager.ftl
 * Admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management")
public class EmployeeManagementController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeManagementController.class);

    private CustomUtils utils;

    @Autowired
    private AdminService adminService;

    /**
     * Retrieves /admin/employee_management/employee_manager.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_manager", method = RequestMethod.GET)
    public ModelAndView loadPage() {

        showRequestLog("employee_manager");

        /*
         * excluding self editing ability by removing current user from list
         */
        List<EmployeeEntity> entityList =  adminService.getAllEmployee();
        List<EmployeeEntity> filteredList = new ArrayList<>();
        for(EmployeeEntity entity: entityList) {
            if(!entity.getLogin().equals(getUserName())) {
                filteredList.add(entity);
            }
        }
        return new ModelAndView("admin/employee_management/employee_manager", "employee", filteredList);
    }
}
