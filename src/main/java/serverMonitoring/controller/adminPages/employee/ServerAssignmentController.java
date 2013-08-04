package serverMonitoring.controller.adminPages.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ServerEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles and retrieves /admin/employee_management/serv_assignment.ftl
 * Admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management/serv_assignment")
public class ServerAssignmentController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeManagementController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves /WEB-INF/ftl/admin/employee_management/serv_assignment.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView loadPage(@PathVariable("id") Long id) {

        showRequestLog("serv_assignment");
        if (id != null && employeeService.getEmployeeById(id) != null) {

            ModelAndView model = new ModelAndView("admin/employee_management/serv_assignment");
            model.addObject("employee", employeeService.getEmployeeById(id));
            model.addObject("assignedServers", employeeService.findAllByResponsibleId(id));
            model.addObject("availableServers", employeeService.findAllByResponsibleId((long) 0));
            return model;
        } else {
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }

    @RequestMapping(value = "/{id}/{name}", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("name") ServerEntity serverEntity,
                                 BindingResult errors,
                                 SessionStatus status,
                                 HttpServletRequest request) {


        return new ModelAndView("redirect:/employee_management/employee_manager");
    }

    /**
     * Action on button "Cancel" pressed.
     * @return redirect to employee_management page
     */
    @RequestMapping(value = "/{id}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("serv_assignment");
        return new ModelAndView("redirect:/employee_management/employee_manager");
    }
}
