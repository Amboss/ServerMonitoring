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

import java.util.ArrayList;
import java.util.List;

/**
 * Handles and retrieves the ROLE_ADMIN admin/employee_management/employee_manager.ftl
 * A admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management")
public class EmployeeManagementController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeManagementController.class);

    private String catalogPath = "admin/employee_management/";

    @Autowired
    private AdminService adminService;

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_manager.ftl
     *
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

        return new ModelAndView(catalogPath + "employee_manager", "employee", filteredList);
    }


    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_removal.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/employee_removal/{id}", method = RequestMethod.POST)
    public ModelAndView getEmployeeRemovalPage() {
        showRequestLog("employee_removal");
        return new ModelAndView(catalogPath + "employee_removal");
    }


    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/serv_assignment.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/serv_assignment", method = RequestMethod.GET)
    public ModelAndView getServerAssignmentPage() {
        showRequestLog("serv_assignment");
        return new ModelAndView(catalogPath + "serv_assignment");
    }

}
