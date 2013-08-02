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
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.RegistrSimplFormModel;
import serverMonitoring.util.web.validations.ServerUpdateValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Handles and retrieves /admin/server_management/serv_update.ftl
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/server_management/serv_update")
public class ServerEditController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(ServerManagementController.class);

    private List<String> activeMap = Arrays.asList("Active", "Not active");

    private ServerUpdateValidator serverUpdateValidator;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    public void setValidator(ServerUpdateValidator serverUpdateValidator) {
        this.serverUpdateValidator = serverUpdateValidator;
    }

    /**
     * Retrieves /admin/server_management/serv_update.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ModelAndView loadPage(@PathVariable("name") String name) {
        showRequestLog("serv_update");

        if (name != null && employeeService.getServerByName(name) != null) {
            ServerEntity serverEntity = employeeService.getServerByName(name);
            RegistrSimplFormModel simplFormModel = new RegistrSimplFormModel();

            /*
             * translating active state to integer
             */
            if (serverEntity.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView model = new ModelAndView("/admin/server_management/serv_update");
            // providing form info
            model.addObject("activeState", simplFormModel);
            model.addObject("serverEntity", serverEntity);
            model.addObject("activeMap", activeMap);
            return model;

        } else {
            return new ModelAndView("redirect:/server_management/serv_manager");
        }
    }

    /**
     * Handles Submit action on /admin/server_management/serv_update.ftl
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("activeState") RegistrSimplFormModel simplFormModel,
            @ModelAttribute("serverEntity") ServerEntity serverEntity,
            BindingResult errors,
            SessionStatus status,
            HttpServletRequest request) {

        showRequestLog("serv_update");

        /**
         * form validation
         */
        this.serverUpdateValidator.validate(serverEntity, errors);

        if (errors.hasErrors()) {

            ModelAndView errorModelAndView = new ModelAndView("/admin/server_management/serv_update");
            // providing form info
            errorModelAndView.addObject("activeState", simplFormModel);
            errorModelAndView.addObject("serverEntity", serverEntity);
            errorModelAndView.addObject("activeMap", activeMap);
            return errorModelAndView;
        } else {
            /*
             * translating active state to integer
             */
            if (simplFormModel.getState().equals("Active")) {
                serverEntity.setActive(1);
            } else {
                serverEntity.setActive(0);
            }
            /**
             * updating server
             */
            adminService.updateServer(serverEntity);
            status.setComplete();
            return new ModelAndView("redirect:/server_management/serv_manager");
        }
    }

    /**
     * Action on button "Cancel" pressed.
     *
     * @return redirect to monitoring page
     */
    @RequestMapping(value = "/{name}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("serv_manager");
        return new ModelAndView("redirect:/server_management/serv_manager");
    }
}

