package serverMonitoring.controller.employeePages;

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
import serverMonitoring.controller.CustomAbstractController;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ChangePasswordObject;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.util.web.validations.PasswordValidator;

/**
 * Handles and retrieves /WEB-INF/ftl/employee/password_update.ftl
 *
 * @TODO fix error messages
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee/password_update")
public class PasswordUpdatePage extends CustomAbstractController {

    protected static Logger logger = Logger.getLogger(PasswordUpdatePage.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private String catalogPath = "employee/";
    private EmployeeService employeeService;
    private PasswordValidator passwordValidator;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPasswordValidator(PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    /**
     * @return password_update page.
     *  - adding ChangePasswordObject
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {
        showRequestLog("password_update");
        ModelAndView model = new ModelAndView("/employee/password_update");
        model.addObject("pass_object", new ChangePasswordObject());
        return model;
    }

    /**
     * Action on button "Cancel" pressed.
     *  - return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    protected ModelAndView processCancel() {
        showRequestLog("monitoring");
        return new ModelAndView("/employee/monitoring");
    }

    /**
     * Action on button "Change password" pressed.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("pass_object")
                                 ChangePasswordObject changePasswordObject,
                                 BindingResult errors,
                                 SessionStatus status) {

        //passwordValidator.validate(changePasswordObject, errors);
        if (errors.hasErrors()) {
            return new ModelAndView("/employee/password_update", "pass_object", errors);
        } else {
            EmployeeEntity entity = employeeService.getEmployeeByLogin(getUserName());
            employeeService.changePassword(entity, changePasswordObject.getNewPassword());
            status.setComplete();
        }
        return new ModelAndView("/employee/monitoring");
    }
}
