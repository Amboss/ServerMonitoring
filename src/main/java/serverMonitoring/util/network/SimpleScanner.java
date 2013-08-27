package serverMonitoring.util.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Interface for custom network scan
 */
public interface SimpleScanner {

    /**
     * simple port scanner
     */
    public boolean doSimpleScan(final String ip, final int port, final int timeout);

    /**
     * scanner for multiple contacts
     */
    public Future<Boolean> doMultipleScan(final ExecutorService es, final String ip,
                                          final int port, final int timeout);


}
