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
import serverMonitoring.model.PasswordUpdateModel;
import serverMonitoring.util.web.validations.PasswordUpdateValidator;

/**
 * Handles and retrieves /WEB-INF/ftl/admin/admin_update_pass.ftl
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/admin_update_pass")
public class AdminPasswordUpdateController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(AdminPasswordUpdateController.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private String catalogPath = "/admin";
    private EmployeeService employeeService;
    private PasswordUpdateValidator passwordUpdateValidator;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPasswordUpdateValidator(PasswordUpdateValidator passwordUpdateValidator) {
        this.passwordUpdateValidator = passwordUpdateValidator;
    }

    /**
     * @return password_update page.
     *  - adding PasswordUpdateModel
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {
        showRequestLog("password_update");
        ModelAndView model = new ModelAndView("/admin/admin_update_pass");
        model.addObject("pass_object", new PasswordUpdateModel());
        return model;
    }

    /**
     * Action on button "Change password" pressed.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("pass_object")
                                     PasswordUpdateModel changePasswordObject,
                                 BindingResult errors,
                                 SessionStatus status) {

        passwordUpdateValidator.validate(changePasswordObject, errors);
        if (errors.hasErrors()) {
            return new ModelAndView("/admin/admin_update_pass", "pass_object", errors);
        } else {
            EmployeeEntity entity = employeeService.getEmployeeByLogin(getUserName());
            String foo = changePasswordObject.getNewPassword();
            employeeService.updateEmployeePassword(entity, foo);
            status.setComplete();
        }
        return new ModelAndView("/employee/monitoring");
    }
}
