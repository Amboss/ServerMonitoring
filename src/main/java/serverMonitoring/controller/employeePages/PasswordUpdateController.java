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
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.PasswordUpdateModel;
import serverMonitoring.util.web.validations.PasswordUpdateValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles and retrieves /WEB-INF/ftl/employee/password_update.ftl
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/employee/password_update")
public class PasswordUpdateController extends CustomAbstractController {

    protected static Logger logger = Logger.getLogger(PasswordUpdateController.class);
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
    private String catalogPath = "employee/";
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
    public ModelAndView loadPage(HttpServletRequest request,
                                 HttpServletResponse response) {
        showRequestLog("Received request to show password_update page");
        return new ModelAndView("/employee/password_update",
                "passUpdate", new PasswordUpdateModel());
    }


    /**
     * Action on button "Cancel" pressed.
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    protected ModelAndView onCancel(HttpServletRequest request) {
        showRequestLog("monitoring");
        return new ModelAndView("/employee/monitoring");
    }

    /**
     * Action on button "Change password" pressed.
     *
     * @return ftl page regarding of the validation result
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("passUpdate")
                                 PasswordUpdateModel passwordUpdateModel,
                                 BindingResult errors,
                                 SessionStatus status) {

        passwordUpdateValidator.validate(passwordUpdateModel, errors);

        if (errors.hasErrors()) {
            return new ModelAndView("/employee/password_update",
                    "passUpdate", passwordUpdateModel);
        } else {
            EmployeeEntity entity = employeeService.getEmployeeByLogin(getUserName());
            employeeService.updateEmployeePassword(entity, passwordUpdateModel.getNewPassword());
            status.setComplete();
        }
        return new ModelAndView("/employee/monitoring");
    }
}
