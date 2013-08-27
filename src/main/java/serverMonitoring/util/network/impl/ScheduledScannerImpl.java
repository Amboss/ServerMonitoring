package serverMonitoring.util.network.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import serverMonitoring.logic.service.AdminService;
import serverMonitoring.logic.service.EmployeeService;
import serverMonitoring.model.ServerEntity;
import serverMonitoring.model.ftl.SystemSettingsModel;
import serverMonitoring.model.serverStateEnum.ServerState;
import serverMonitoring.util.network.ScheduledScanner;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Sends GET request to @param ip: @param port with Apache HttpClient
 */
public class ScheduledScannerImpl implements ScheduledScanner {

    protected static Logger logger = Logger.getLogger(ScheduledScannerImpl.class);

    protected final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0";

    private ScheduledFuture<?> myTask;

    private ScheduledExecutorService scheduler;

    private static final int MYTHREADS = 30;

    private Date date = new Date();

    private Timestamp timestamp = new Timestamp(date.getTime());

    private AdminService adminService;

    private EmployeeService employeeService;

    public ScheduledScannerImpl() {

    }

    @Autowired
    public void initAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private List<ServerEntity> listToScan = adminService.getAllServers();

    private SystemSettingsModel settings = employeeService.getSettingsByName("default");

    /**
     * Sends GET request to @param ip: @param port with Apache HttpClient
     * <p/>
     * param ip      - must contain URL or IP of target server;
     * param port    - for server target Port;
     * param timeout - sets the time for request timeout before no respond;
     *
     * ServerState enum:
     * OK - server is up and responding correctly
     * WARN  - server is running, but returns a response to the HTTP - code different than 200
     * FAIL - the server is not responding, or responds with HTTP code, such as 500
     * <p/>
     * - all other info: http://docs.oracle.com/javaee/6/api/javax/ws/rs/core/Response.Status.html
     */
    public void executeScanner() {

        // setting task to execute;
        scheduler = Executors.newScheduledThreadPool(MYTHREADS);

        myTask = scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                // iterating list of all servers
                for (ServerEntity serverEntity : listToScan) {

                    // check if state of server is active
                    if (serverEntity.getActive().equals(1)) {

                        ServerState state;

                        try {
                            // setting URL or IP eith Port of target address
                            URL obj = new URL(serverEntity.getAddress() + ":" + serverEntity.getPort());

                            // establishing connection
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                            con.setRequestMethod("GET");

                            // setting timeout of response
                            con.setConnectTimeout(settings.getTimeoutOfRespond());

                            // add request header
                            con.setRequestProperty("User-Agent", USER_AGENT);

                            if (con.getResponseCode() == 200) {
                                state = ServerState.OK;
                            } else if (con.getResponseCode() == 500) {
                                state = ServerState.FAIL;   // Internal Server Error
                            } else {
                                state = ServerState.WARN;   // BAD_REQUEST or other conflict
                            }
                        } catch (Exception e) {
                            state = ServerState.FAIL;
                        }

                        // saving response from server
                        serverEntity.setState(state);
                        serverEntity.setResponse(state.toString());
                        serverEntity.setLastCheck(timestamp);
                        adminService.updateServer(serverEntity);
                    }   // if
                }   // run()
            }   // for
            /*
             * Scheduling Fixed Rate of scan
             *
             * param initialDelay the time to delay first execution;
             * param period the period between successive executions;
             * param unit the time unit of the initialDelay and period parameters;
             */
        }, 0, settings.getServerScanInterval(), TimeUnit.SECONDS);   // scheduler
    }   // executeScanner()
}
