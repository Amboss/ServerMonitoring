package serverMonitoring.model.serverStateEnum;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 *
 * ENUM vars to declare Server Status
 */

public enum ServerState {
    OK,  // - server is up and responding correctly
    WARN,// - server is running, but returns a response to the HTTP - code different than 20
    FAIL // - the server is not responding, or responds with HTTP code, such as 500
}
