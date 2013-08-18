package serverMonitoring.controller.adminPages.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.ServersListModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     * Retrieves /admin/employee_management/serv_assignment.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView loadPage(@PathVariable("id") Long id) {

        showRequestLog("serv_assignment");
        if (id != null) {

            ModelAndView model = new ModelAndView("admin/employee_management/serv_assignment");
            model.addObject("employee", employeeService.getEmployeeById(id));
            model.addObject("assignedServers", employeeService.findAllByResponsibleId(id));
            model.addObject("availableServers", employeeService.findAllByResponsibleId((long) 0));
            return model;
        } else {
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }

    /**
     * Handles onSubmit action
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public @ResponseBody Map<String, ? extends Object> onSubmit(
            @ModelAttribute("serversListModel") ServersListModel serversListModel,
            @PathVariable("id") Long responsibleId,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response,
            SessionStatus status) throws ParseException {

        if (!result.hasErrors()) {
            if (serversListModel.getAvailableServers() != null) {
                for (String id : serversListModel.getAvailableServers()) {
                    ServerEntity entity = employeeService.getServerById((long)Integer.parseInt(id));
                    entity.setResponsible(responsibleId);
                    adminService.updateServer(entity);
                }
            }

            if (serversListModel.getAssignedServers() != null) {
                for (String id : serversListModel.getAssignedServers()) {
                    ServerEntity entity = employeeService.getServerById((long)Integer.parseInt(id));
                    entity.setResponsible((long) 0);
                    adminService.updateServer(entity);
                }
            }
            status.setComplete();
            //return new ModelAndView("redirect:/employee_management/employee_manager");


        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //return new ModelAndView("redirect:/employee_management/serv_assignment");
        }
            return null;
    }

    /**
     * Action on button "Cancel" pressed.
     *
     * @return redirect to employee_management page
     */
    @RequestMapping(value = "/{id}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("serv_assignment");
        return new ModelAndView("redirect:/employee_management/employee_manager");
    }

        public void redirect(HttpServletRequest request,
                         HttpServletResponse response,
                         String path) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new BadCredentialsException("Redirect error!");
        }
    }
}
