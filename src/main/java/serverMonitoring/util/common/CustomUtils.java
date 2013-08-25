package serverMonitoring.util.common;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Custom Utils
 */
public interface CustomUtils {

    /**
     * Operation for random Strings generator
     */
    public Timestamp getCurrentTimeStamp();

    /**
     * Operation for random Strings generator
     */
    public String getNewRandomGeneratedPassword();

    /**
     * invalidate provided user session
     */
    public void setUserSessionInvalidated(Long id, HttpServletRequest request);

    /**
     * scan object to contain Digits
     */
    public boolean scanForDigits(Object value);

    /**
     * custom Regex Mutcher
     */
    public boolean getRegexMatch(Object value, String regex);
}
