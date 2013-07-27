package serverMonitoring.util.common.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import serverMonitoring.util.common.CustomUtils;

import java.sql.Timestamp;

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
     * TODO invalidate session
     */
    @Override
    public void setUserSessionInvalidated() {

//        HttpSession session = se.getSession();
//        ServletContext context = session.getServletContext();
//        HashMap activeUsers = (HashMap)context.getAttribute("activeUsers");
//        activeUsers.put(session.getId(), session);
//        context.setAttribute("activeUsers", activeUsers);

        //in sessionCreated method of sessionListner in i successsfully get the list of active user's name and there session id but when i do like that

//        HttpSessionContext context=request.getSession().getSessionContext();
//        ServletContext sc=request.getSession().getServletContext();
//        HashMap activeUsers = (HashMap)sc.getAttribute("activeUsers");
//        HttpSession session=request.getSession();
//        if(activeUsers.containsKey(this.sessionID)==true){ session.invalidate();
        }
}
