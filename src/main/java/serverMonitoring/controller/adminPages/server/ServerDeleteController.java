package serverMonitoring.controller.adminPages.server;

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

import javax.servlet.http.HttpServletRequest;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/server_management/serv_removal")
public class ServerDeleteController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(ServerDeleteController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    /**
     * Retrieves /WEB-INF/ftl/admin/server_management/serv_removal.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "{name}")
    public ModelAndView loadPage(@PathVariable("name") String name){
        showRequestLog("serv_removal");
        return new ModelAndView("admin/server_management/serv_removal",
                "server", employeeService.getServerByName(name));
    }

    /**
     * Handles Submit action on /admin/server_management/serv_removal.ftl
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("name") String name,
                                 BindingResult result,
                                 HttpServletRequest request,
                                 SessionStatus status) {
        showRequestLog("serv_removal");

        if (employeeService.getServerByName(name) != null) {

            adminService.deleteServer(name);
            status.setComplete();
        }
        return new ModelAndView("redirect:/server_management/serv_manager");
    }


    /**
     * Action on button "Cancel" pressed.
     * @return redirect to monitoring page
     */
    @RequestMapping(value = "/{id}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("serv_manager");
        return new ModelAndView("redirect:/server_management/serv_manager");
    }
}
