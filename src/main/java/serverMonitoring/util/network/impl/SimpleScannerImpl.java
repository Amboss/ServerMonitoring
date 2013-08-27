package serverMonitoring.util.network.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import serverMonitoring.util.network.SimpleScanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Class handel's all network scans
 */
@Component
public class SimpleScannerImpl implements SimpleScanner {

    protected static Logger logger = Logger.getLogger(SimpleScannerImpl.class);

    /**
     * simple port scanner
     *
     * @param ip      - must contain URL or IP of target server;
     * @param port    - for server target Port;
     * @param timeout - sets the time for request timeout before no respond;
     * @return boolean
     */
    public boolean doSimpleScan(final String ip, final int port, final int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * multiple scanner
     *
     * @param es      - represents an asynchronous tasks in the background.
     * @param ip      - must contain URL or IP of target server;
     * @param port    - for server target Port;
     * @param timeout - sets the time for request timeout before no respond;
     * @return boolean
     */
    public Future<Boolean> doMultipleScan(final ExecutorService es, final String ip,
                                          final int port, final int timeout) {

        return es.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

}

