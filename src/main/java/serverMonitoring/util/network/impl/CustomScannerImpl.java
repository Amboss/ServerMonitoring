package serverMonitoring.util.network.impl;

import org.springframework.stereotype.Component;
import serverMonitoring.util.network.CustomScanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * User: serge
 */
@Component
public class CustomScannerImpl implements CustomScanner {

    /**
     * simple port scanner
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
