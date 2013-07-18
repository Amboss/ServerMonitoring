package serverMonitoring.util.common;

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
}
