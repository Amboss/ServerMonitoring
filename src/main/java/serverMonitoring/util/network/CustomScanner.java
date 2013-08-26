package serverMonitoring.util.network;

import serverMonitoring.model.serverStateEnum.ServerState;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * User: serge
 */
public interface CustomScanner {

    /**
     * simple port scanner
     */
    public boolean doSimpleScan(final String ip, final int port, final int timeout);

    /**
     * scanner for multiple contacts
     */
    public Future<Boolean> doMultipleScan(final ExecutorService es, final String ip,
                                          final int port, final int timeout);
    /**
     * Sends GET request to @param ip: @param port with Apache HttpClient
    */
    public ServerState sendGetServerState(final String ip, final int port,
                                          final int timeout) throws IOException;
}
