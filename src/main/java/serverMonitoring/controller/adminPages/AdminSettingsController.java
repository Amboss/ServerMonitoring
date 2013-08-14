package serverMonitoring.controller.adminPages;

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
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ftl.SystemSettingsModel;
import serverMonitoring.util.web.validations.SettingsUpdateValidator;

/**
 * Handles admin/settings/change_settings.ftl
 * A admin must be log-in first before he can access these pages.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/settings/change_settings")
public class AdminSettingsController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(AdminSettingsController.class);

    private SettingsUpdateValidator settingsUpdateValidator;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Initiating SettingsUpdateValidator
     */
    @Autowired
    public void setValidator(SettingsUpdateValidator settingsUpdateValidator) {
        this.settingsUpdateValidator = settingsUpdateValidator;
    }

    /**
     * Retrieves /WEB-INF/ftl/admin/settings/change_settings.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {
        showRequestLog("change_settings");

        SystemSettingsModel settingsModel = employeeService.getSettingsByName("default");
        return new ModelAndView("/admin/settings/change_settings", "settings", settingsModel);
    }

    /**
     * Retrieves /WEB-INF/ftl/admin/settings/change_settings.ftl
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("settings") SystemSettingsModel settingsModel,
            BindingResult errors,
            SessionStatus status) {

        showRequestLog("change_settings");

        /**
         * form validation
         */
        settingsUpdateValidator.validate(settingsModel, errors);

        if (errors.hasErrors()) {
            return new ModelAndView("/admin/settings/change_settings", "settings", settingsModel);
        } else {
            adminService.updateSettings(settingsModel);
            status.setComplete();
            return new ModelAndView("redirect:/employee/monitoring");
        }
    }

    /**
     * Action on button "Cancel" pressed.
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("monitoring");
        return new ModelAndView("redirect:/employee/monitoring");
    }
}
