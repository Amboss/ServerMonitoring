package serverMonitoring.util.common.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import serverMonitoring.util.common.CustomUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Class handel's  methods for common functionality
 */
@Component
public class CustomUtilsImpl implements CustomUtils {

    /**
     * getting current date & time for SQL
     *
     * @return current Timestamp
     */
    public Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }

    /**
     * Operation for random Strings generator
     *
     * @return random Strings with customised length
     */
    public String getNewRandomGeneratedPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * invalidate provided user session
     * TODO finish invalidation
     */
    @Override
    public void setUserSessionInvalidated(Long id, HttpServletRequest request) {


        ServletContext servletContext = request.getSession().getServletContext();
        HashMap activeUsers = (HashMap) servletContext.getAttribute("activeUsers");
        HttpSession session = request.getSession(false);
        if (activeUsers.containsKey(id)) {
            session.removeAttribute("loggedUser");
            session.invalidate();
        }
    }


}
