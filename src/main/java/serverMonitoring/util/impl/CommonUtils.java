package serverMonitoring.util.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import serverMonitoring.util.CustomUtils;

import java.sql.Timestamp;

/**
 * Class handel's  methods for common functionality
 */
@Component
public class CommonUtils implements CustomUtils {

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
}
