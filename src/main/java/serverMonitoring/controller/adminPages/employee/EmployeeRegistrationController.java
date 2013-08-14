package serverMonitoring.controller.adminPages.employee;

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
import serverMonitoring.controller.adminPages.AbstractAdminController;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.RegistrSimplFormModel;
import serverMonitoring.util.common.CustomUtils;
import serverMonitoring.util.mail.CustomMailDelivery;
import serverMonitoring.util.web.validations.EmployeeRegistrationValidator;

import javax.mail.SendFailedException;
import java.util.Arrays;
import java.util.List;

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

    private CustomMailDelivery customMailDelivery;

    private CustomUtils customUtils;

    // providing list of options for "active" formRadioButtons
    private List<String> activeMap = Arrays.asList("Active", "Not active");

    private List<String> adminMap = Arrays.asList("Regular", "Admin");

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
     * Action on button "Cancel" pressed.
     *
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String onCancel() {
        showRequestLog("monitoring");
        return "redirect:/employee_management/employee_manager";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage() {

        showRequestLog("employee_registr");

        ModelAndView model = new ModelAndView("admin/employee_management/employee_registr");
        model.addObject("newEmployee", new EmployeeEntity());
        model.addObject("activeMap", activeMap);
        model.addObject("adminMap", adminMap);
        model.addObject("simplFormModel", new RegistrSimplFormModel());

        return model;
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_registr.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("simplFormModel") RegistrSimplFormModel simplFormModel,
            @ModelAttribute("newEmployee") EmployeeEntity newEmployee,
            BindingResult errors, SessionStatus status) {

        showRequestLog("employee_registr");

        /**
         * form validation
         */
        employeeRegistrationValidator.validate(newEmployee, errors);

        if (errors.hasErrors()) {

            ModelAndView errorModelAndView = new ModelAndView("/admin/employee_management/employee_registr");
            // providing form info
            errorModelAndView.addObject("newEmployee", newEmployee);
            errorModelAndView.addObject("simplFormModel", simplFormModel);

            // providing list of options for "active" formRadioButtons
            errorModelAndView.addObject("activeMap", activeMap);
            errorModelAndView.addObject("adminMap", adminMap);

            return errorModelAndView;
        } else {
            String newPass = customUtils.getNewRandomGeneratedPassword();
            newEmployee.setPassword(passwordEncoder.encodePassword(newPass, null));

            //translating active state to integer
            if (simplFormModel.getActiveState().equals("Active")) {
                newEmployee.setActive(1);
            } else {
                newEmployee.setActive(0);
            }

            // translating role state to integer
            if (simplFormModel.getLevel().equals("Admin")) {
                newEmployee.setAdmin(1);
            } else {
                newEmployee.setAdmin(0);
            }

            // registration of new employee
            adminService.registerEmployee(newEmployee);

            // sending email with new password
            try {
                customMailDelivery.sendMail("huskyserge@gmail.com",
                        newEmployee.getEmail(),
                        "You are greeted by notifying system of Server Monitoring Service!",
                        "Congratulations, you've got access to the system and can now get to work.\n" +
                                "\n" + "Your user name: " + newEmployee.getLogin() +
                                "\n" + "Your new password is: " + newPass +
                                "\n" + "\n" + "Server Monitoring Service");
            } catch (SendFailedException ignore) {
            }
            status.setComplete();
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }
}
