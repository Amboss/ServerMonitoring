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
import serverMonitoring.model.ftl.RegistrSimplFormModel;
import serverMonitoring.util.common.CustomUtils;
import serverMonitoring.util.web.validations.EmployeeUpdateValidatior;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Handles and retrieves the ROLE_ADMIN admin/employee_management/employee_manager.ftl
 * A admin must be log-in first before he can access these page.
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/employee_management/employee_update")
public class EmployeeEditController extends AbstractAdminController {

    protected static Logger logger = Logger.getLogger(EmployeeEditController.class);

    private List<String> activeMap = Arrays.asList("Active", "Not active");

    private EmployeeUpdateValidatior employeeUpdateValidatior;

    private CustomUtils utils;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    public void setValidator(EmployeeUpdateValidatior employeeUpdateValidatior) {
        this.employeeUpdateValidatior = employeeUpdateValidatior;
    }

    @Autowired
    public void setUtils(CustomUtils utils) {
        this.utils = utils;
    }

    /**
     * Retrieves /admin/employee_management/employee_update.ftl
     * @return the name of the FreeMarker template page
     */
    @RequestMapping(value = "/{id}")
    public ModelAndView loadPage(@PathVariable("id") Long id) {

        showRequestLog("employee_update");
        if (id != null) {

            EmployeeEntity employeeEntity = employeeService.getEmployeeById(id);
            RegistrSimplFormModel simplFormModel = new RegistrSimplFormModel();

            /*
             * translating active state to integer
             */
            if (employeeEntity.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView model = new ModelAndView("/admin/employee_management/employee_update");
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
     * Handles Submit action on /admin/employee_management/employee_update.ftl
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView onSubmit(
            @ModelAttribute("activeState") RegistrSimplFormModel simplFormModel,
            @ModelAttribute("employeeEntity") EmployeeEntity employeeEntity,
            BindingResult errors,
            SessionStatus status,
            HttpServletRequest request) {

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
        employeeUpdateValidatior.validate(employeeEntity, errors);

        if (errors.hasErrors()) {

            /*
             * translating active state to integer
             */
            if (employeeEntity.getActive().equals(1)) {
                simplFormModel.setState("Active");
            } else {
                simplFormModel.setState("Not active");
            }

            ModelAndView errorModelAndView = new ModelAndView("/admin/employee_management/employee_update");
            // providing form info
            errorModelAndView.addObject("activeState", simplFormModel);
            errorModelAndView.addObject("employeeEntity", employeeEntity);
            errorModelAndView.addObject("activeMap", activeMap);
            return errorModelAndView;

        } else {

            /**
             * invalidating employee session
             */
            if (employeeEntity.getActive().equals(0)) {
                HttpSession session = request.getSession();
                session.invalidate();
            }

            /**
             * updating employee
             */
            adminService.updateEmployee(employeeEntity);
            status.setComplete();
            return new ModelAndView("redirect:/employee_management/employee_manager");
        }
    }

    /**
     * Action on button "Cancel" pressed.
     *
     * @return redirect to monitoring page
     */
    @RequestMapping(value = "/{id}", params = "cancel", method = RequestMethod.POST)
    public ModelAndView onCancel() {
        showRequestLog("monitoring");
        return new ModelAndView("redirect:/employee_management/employee_manager");
    }
}
