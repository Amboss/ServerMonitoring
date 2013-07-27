package serverMonitoring.controller.adminPages.employee;

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
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.ftl.EmployeeRegistrSimplFormModel;

import java.util.Arrays;
import java.util.List;

/**
 * Handles and retrieves the ROLE_ADMIN admin/employee_management/employee_manager.ftl
 * A admin must be log-in first before he can access these page.
 * TODO add validation
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management/employee_update")
public class EmployeeEditController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeEditController.class);

//    private EmployeeRegistrationValidator employeeRegistrationValidator;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

//    @Autowired
//    public void setValidator(EmployeeRegistrationValidator employeeRegistrationValidator) {
//        this.employeeRegistrationValidator = employeeRegistrationValidator;
//    }

    /**
     * Action on button "Cancel" pressed.
     *
     * @return redirect to monitoring page
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    protected String onCancel() {
        showRequestLog("monitoring");
        return "redirect:/employee_management/employee_manager";
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_update.ftl
     *
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}")
    public ModelAndView loadPage(@PathVariable("id") Long id) {
        showRequestLog("employee_update");
        if (id != null) {

            EmployeeEntity employeeEntity = employeeService.getEmployeeById(id);
            EmployeeRegistrSimplFormModel simplFormModel = new EmployeeRegistrSimplFormModel();

            // providing list of options for "active" formRadioButtons
            List<String> activeMap = Arrays.asList("Active", "Not active");

            /*
             * translating active state to integer
             */
            if (employeeEntity.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView model = new ModelAndView("admin/employee_management/employee_update");
            // providing form info
            model.addObject("activeState", simplFormModel);
            model.addObject("employeeEntity", employeeEntity);
            model.addObject("activeMap", activeMap);
            return model;

        } else {
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }

    /**
     * Handles and retrieves /WEB-INF/ftl/admin/employee_management/employee_update.ftl
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("activeState") EmployeeRegistrSimplFormModel simplFormModel,
            @ModelAttribute("employeeEntity") EmployeeEntity employeeEntity,
            BindingResult errors, SessionStatus status) {

        showRequestLog("employee_registr");

        /*
         * translating active state to integer
         */
        if (simplFormModel.getState().equals("Active")) {
            employeeEntity.setActive(1);
        } else {
            employeeEntity.setActive(0);
        }

        /**
         * form validation
         */
//        employeeRegistrationValidator.validate(employeeEntity, errors);

        if (errors.hasErrors()) {

            // providing list of options for "active" formRadioButtons
            List<String> activeMap = Arrays.asList("Active", "Not active");

            /*
             * translating active state to integer
             */
            if (employeeEntity.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView model = new ModelAndView("admin/employee_management/employee_update");
            // providing form info
            model.addObject("activeState", simplFormModel);
            model.addObject("employeeEntity", employeeEntity);
            model.addObject("activeMap", activeMap);
            return model;
        } else {

            /**
             * registration of new employee
             */
            adminService.updateEmployee(employeeEntity);
            status.setComplete();
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }
}
