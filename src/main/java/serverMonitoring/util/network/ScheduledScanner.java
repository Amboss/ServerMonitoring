package serverMonitoring.util.network;

/**
 * Scanner for multiple threads
 */
public interface ScheduledScanner {
    /**
     * Sends GET request to @param ip: @param port with Apache HttpClient
     */
    public void executeScanner();
}
