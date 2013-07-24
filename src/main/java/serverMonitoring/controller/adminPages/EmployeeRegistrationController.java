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
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.util.common.CustomUtils;
import serverMonitoring.util.mail.CustomMailDelivery;
import serverMonitoring.util.web.validations.EmployeeRegistrationValidator;
import serverMonitoring.util.web.validations.PasswordRecoveryValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handles and retrieves the ROLE_ADMIN /admin/employee_management/employee_registr.ftl
 * A admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management/employee_registr")
public class EmployeeRegistrationController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeManagementController.class);

    private EmployeeRegistrationValidator employeeRegistrationValidator;

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

    private AnonymousService anonymousService;

    private PasswordRecoveryValidator passwordRecoveryValidator;

    private CustomMailDelivery customMailDelivery;

    private CustomUtils customUtils;

    @Autowired
    private AdminService adminService;

    @Autowired
    public void setCustomUtils(CustomUtils customUtils) {
        this.customUtils = customUtils;
    }

    @Autowired
    public void setCustomMailDelivery(CustomMailDelivery customMailDelivery) {
        this.customMailDelivery = customMailDelivery;
    }

    @Autowired
    public void setValidator(EmployeeRegistrationValidator employeeRegistrationValidator) {
        this.employeeRegistrationValidator = employeeRegistrationValidator;
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setEmployeeRegistrationPage(HttpServletRequest request,
                                                    HttpServletResponse response) {
        showRequestLog("employee_registr");

        // providing list of options for "active" formRadioButtons
        Map activeMap = new LinkedHashMap();
        activeMap.put("Active", 1);
        activeMap.put("Not active", 0);

        // providing list of options for "active" formRadioButtons
        Map  adminMap = new LinkedHashMap();
        adminMap.put("Admin", 1);
        adminMap.put("Regular", 0);

        ModelAndView model =  new ModelAndView("admin/employee_management/employee_registr");
        model.addObject("activeMap", activeMap);
        model.addObject("adminMap", adminMap);
        model.addObject("newEmployee", new EmployeeEntity());
        return model;
    }

    /**
     * Action on button "Cancel" pressed.
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    protected String onCancel(HttpServletRequest request,
                                         HttpServletResponse response) {
        showRequestLog("monitoring");
        return "redirect:/employee_management/employee_manager";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView getEmployeeRegistrationPage(@ModelAttribute("newEmployee")
                                                    EmployeeEntity employeeEntity,
                                                    BindingResult errors,
                                                    SessionStatus status) {
        showRequestLog("employee_registr");
//      employeeRegistrationValidator.validate(employeeEntity, errors);

        if (errors.hasErrors()) {
            return new ModelAndView("/admin/employee_management/employee_registr",
                    "newEmployee", employeeEntity);
        } else {
            String newPass = customUtils.getNewRandomGeneratedPassword();
            employeeEntity.setPassword(newPass);
            /**
             * registration of new employee
             */
            adminService.registerEmployee(employeeEntity);

            /**
             * sending email with new password
             */
            customMailDelivery.sendMail("huskyserge@gmail.com",
                    employeeEntity.getEmail(),
                    "You are greeted by notifying system of Server Monitoring Service!",
                    "Congratulations, you've got access to the system and can now get to work.\n" +
                            "\n" + "Your user name: " + employeeEntity.getLogin() +
                            "\n" + "Your new password is: " + newPass +
                            "\n" + "\n" + "Server Monitoring Service");

            status.setComplete();
        }
        return new ModelAndView("/admin/employee_management/employee_manager");
    }
}
