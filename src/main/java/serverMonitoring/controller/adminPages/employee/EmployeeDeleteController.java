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
import serverMonitoring.util.common.CustomUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles and retrieves the ROLE_ADMIN admin/employee_management/employee_removal.ftl
 * A admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management/employee_removal")
public class EmployeeDeleteController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeDeleteController.class);

    private CustomUtils utils;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public void setUtils(CustomUtils utils) {
        this.utils = utils;
    }

    /**
     * Retrieves /WEB-INF/ftl/admin/employee_management/employee_removal.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}")
    public ModelAndView loadPage(@PathVariable("id") Long id) {
        showRequestLog("employee_removal");

        return new ModelAndView("admin/employee_management/employee_removal",
                "employee", employeeService.getEmployeeById(id));
    }

    /**
     * Handles Submit action on /admin/employee_management/employee_removal.ftl
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("id") Long id, BindingResult result,
                                 HttpServletRequest request,
                                 SessionStatus status) {
        showRequestLog("employee_removal");

        if (employeeService.getEmployeeById(id) != null) {
            adminService.deleteEmployee(id);
//            utils.setUserSessionInvalidated(id, request);
            status.setComplete();
        }
        return new ModelAndView("redirect:/employee_management/employee_manager");
    }

    /**
     * Action on button "Cancel" pressed.
     * @return redirect to monitoring page
     */
    @RequestMapping(value = "/{id}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("monitoring");
        return new ModelAndView("redirect:/employee_management/employee_manager");
    }
}
