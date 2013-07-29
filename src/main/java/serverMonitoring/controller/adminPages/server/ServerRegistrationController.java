package serverMonitoring.controller.adminPages.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.RegistrSimplFormModel;
import serverMonitoring.util.web.validations.ServerRegistrationValidator;

import java.util.Arrays;
import java.util.List;

/**
 * Handles and retrieves the ROLE_ADMIN server_management pages depending on the URI template.
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/server_management/serv_registr")
public class ServerRegistrationController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(ServerRegistrationController.class);

    private List<String> activeMap = Arrays.asList("Active", "Not active");

    private ServerRegistrationValidator serverRegistrationValidator;

    @Autowired
    private AdminService adminService;

    @Autowired
    public void setValidator(ServerRegistrationValidator serverRegistrationValidator) {
        this.serverRegistrationValidator = serverRegistrationValidator;
    }

    /**
     * Handles /admin/server_management/serv_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {
        showRequestLog("serv_registr");

        ModelAndView model = new ModelAndView("/admin/server_management/serv_registr");
        model.addObject("newServer", new ServerEntity());
        model.addObject("activeMap", activeMap);
        model.addObject("activeState", new RegistrSimplFormModel());

        return model;
    }

    /**
     * Action on button "Cancel" pressed.
     *
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String onCancel() {
        showRequestLog("monitoring");
        return "redirect:/server_management/serv_manager";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("activeState") RegistrSimplFormModel simplFormModel,
            @ModelAttribute("newServer") ServerEntity newServer,
            BindingResult errors, SessionStatus status) {

        showRequestLog("employee_registr");
             /*
             * translating active state to integer
             */
        if (simplFormModel.getState().equals("Active")) {
            newServer.setActive(1);
        } else {
            newServer.setActive(0);
        }

        /**
         * form validation
         */
        //serverRegistrationValidator.validate(newServer, errors);

        if (errors.hasErrors()) {

            /*
             * translating active state to integer
             */
            if (newServer.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView errorModelAndView = new ModelAndView("/admin/server_management/serv_registr");
            errorModelAndView.addObject("newServer", newServer);
            errorModelAndView.addObject("activeMap", activeMap);
            errorModelAndView.addObject("activeState", simplFormModel);

            return errorModelAndView;
        } else {

            /**
             * registration of new employee
             */
            adminService.registerServer(newServer);
            return new ModelAndView("redirect:/server_management/serv_manager");
        }
    }
}
