package serverMonitoring.util.common.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import serverMonitoring.util.common.CustomUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * invalidate provided user session
     */
    @Override
    public boolean scanForDigits(Object value) {

        String targetValue = value.toString();

        Pattern textPattern = Pattern.compile("[0-9]");
        Matcher valueMatcher = textPattern.matcher(targetValue);

        if (!valueMatcher.matches()) {
            return false;
        }

        for (int i = 0; i < targetValue.length(); i++) {
            if (!Character.isDigit(targetValue.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * custom Regex Mutcher
     */
    @Override
    public boolean getRegexMatch(Object value, String regex) {

        String targetValue = value.toString();

        Pattern textPattern = Pattern.compile(regex);

        Matcher valueMatcher = textPattern.matcher(targetValue);

        return valueMatcher.matches();
    }
}
