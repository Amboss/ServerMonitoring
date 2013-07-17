package serverMonitoring.controller.passRecoveryPages;

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
import serverMonitoring.logic.service.AnonymousService;
import serverMonitoring.model.EmployeeEntity;
import serverMonitoring.model.PasswordRecoveryObject;
import serverMonitoring.util.CustomUtils;
import serverMonitoring.util.web.validations.PasswordRecoveryValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles and retrieves /WEB-INF/ftl/authorization/password_recovery.ftl
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/auth/password_recovery")
public class PasswordRecoveryController extends CustomAbstractController {

    protected static Logger logger = Logger.getLogger(PasswordRecoveryController.class);

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

    private AnonymousService anonymousService;

    private PasswordRecoveryValidator passwordRecoveryValidator;

    private CustomUtils customUtils;

    @Autowired
    public void setAnonymousService(AnonymousService anonymousService) {
        this.anonymousService = anonymousService;
    }

    @Autowired
    public void setPasswordRecoveryValidator(PasswordRecoveryValidator passwordRecoveryValidator) {
        this.passwordRecoveryValidator = passwordRecoveryValidator;
    }

    @Autowired
    public void setCustomUtils(CustomUtils customUtils) {
        this.customUtils = customUtils;
    }

    /**
     * Retrieves /WEB-INF/ftl/authorization/password_recovery.ftl
     *
     * @return the name of the ftl page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage(HttpServletRequest request,
                                 HttpServletResponse response) {
        showRequestLog("Received request to show password_recovery page");

        return new ModelAndView("/authorization/password_recovery",
                "passRecovery", new PasswordRecoveryObject());

    }

    /**
     * Action on button "Cancel" pressed.
     * @return redirect to index page
     *  TODO CLEAN AFTER PRODUCTION: type='submit' name='cancel'  value='Cancel'
     */
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    protected ModelAndView onCancel(HttpServletRequest request) {
        showRequestLog("monitoring");
        return new ModelAndView("index");
    }

    /**
     * Action on button "Generate new password" pressed.
     *
     * @return ftl page regarding of the validation result
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("passRecovery")
                                 PasswordRecoveryObject passwordRecoveryObject,
                                 BindingResult result,
                                 SessionStatus status) {

        passwordRecoveryValidator.validate(passwordRecoveryObject, result);

        if (result.hasErrors()) {
            return new ModelAndView("/authorization/password_recovery", "passRecovery", passwordRecoveryObject);
        } else {
            EmployeeEntity entity = anonymousService.getEmployeeByEmail(passwordRecoveryObject.getEmail());
            String newPass = customUtils.getNewRandomGeneratedPassword();
            anonymousService.updateEmployeePassword(entity, newPass);
            status.setComplete();
        }
        return new ModelAndView("index");
    }
}
