package serverMonitoring.controller.adminPages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.PasswordUpdateModel;
import serverMonitoring.util.web.validations.PasswordUpdateValidator;

/**
 * Handles and retrieves /WEB-INF/ftl/admin/admin_update_pass.ftl
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/admin_update_pass")
public class AdminPasswordUpdateController  {

    protected static Logger logger = Logger.getLogger(AdminPasswordUpdateController.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private EmployeeService employeeService;
    private PasswordUpdateValidator passwordUpdateValidator;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setValidator(PasswordUpdateValidator passwordUpdateValidator) {
        this.passwordUpdateValidator = passwordUpdateValidator;
    }

    /**
     * @return password_update page.
     *         - adding PasswordUpdateModel
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {
        return new ModelAndView("/admin/admin_update_pass", "passUpdate", new PasswordUpdateModel());
    }

    /**
     * Action on button "Change password" pressed.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("passUpdate") PasswordUpdateModel passUpdate,
                                 BindingResult errors,
                                 SessionStatus status) {
        /**
         * form validation
         */
        passwordUpdateValidator.validate(passUpdate, errors);

        if (errors.hasErrors()) {
            return new ModelAndView("/admin/admin_update_pass", "passUpdate", passUpdate);
        } else {
            EmployeeEntity entity = employeeService.getEmployeeByLogin("admin");
            String foo = passUpdate.getNewPassword();
            employeeService.updateEmployeePassword(entity, foo);
            status.setComplete();
        }
        return new ModelAndView("redirect:/employee/monitoring");
    }
}
