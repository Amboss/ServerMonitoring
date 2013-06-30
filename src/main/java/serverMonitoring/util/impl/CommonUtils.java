package serverMonitoring.util.impl;

import serverMonitoring.util.CustomUtils;

import java.sql.Timestamp;

/**
 * Class handel's  methods for common functionality
 */
public class CommonUtils implements CustomUtils {

    /*
     * getting current date & time for SQL
     */
    public Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }
}
