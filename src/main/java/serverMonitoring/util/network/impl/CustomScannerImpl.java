package serverMonitoring.util.network.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import serverMonitoring.model.serverStateEnum.ServerState;
import serverMonitoring.util.network.CustomScanner;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * User: serge
 */
@Component
public class CustomScannerImpl implements CustomScanner {

    protected static Logger logger = Logger.getLogger(CustomScannerImpl.class);

    final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0";

    /**
     * simple port scanner
     * @param ip - must contain URL or IP of target server;
     * @param port - for server target Port;
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
     * @param es - represents an asynchronous tasks in the background.
     * @param ip - must contain URL or IP of target server;
     * @param port - for server target Port;
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

    /**
     * Sends GET request to @param ip: @param port with Apache HttpClient
     * @param ip - must contain URL or IP of target server;
     * @param port - for server target Port;
     * @param timeout - sets the time for request timeout before no respond;
     * @return ServerState enum:
     *         OK - server is up and responding correctly
     *         WARN  - server is running, but returns a response to the HTTP - code different than 200
     *         FAIL - the server is not responding, or responds with HTTP code, such as 500
     *         <p/>
     *         - all other info: http://docs.oracle.com/javaee/6/api/javax/ws/rs/core/Response.Status.html
     */
    public ServerState sendGetServerState(final String ip, final int port, final int timeout) throws IOException {

        URL obj = new URL(ip + ":" + port);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con.setConnectTimeout(timeout);
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int response = con.getResponseCode();

        if (response == 200) {
            return ServerState.OK;
        } else if (response != 500) {

            // BAD_REQUEST or other conflict
            return ServerState.WARN;
        } else {

            // Internal Server Error
            return ServerState.FAIL;
        }
    }
}

