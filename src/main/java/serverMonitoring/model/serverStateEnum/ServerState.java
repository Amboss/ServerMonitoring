package serverMonitoring.model.serverStateEnum;

/**
 * ENUM vars to declare Server Status
 */

public enum ServerState {
    OK {
        // - server is up and responding correctly
    },
    WARN {
        // - server is running, but returns a response to the HTTP - code different than 20
    },
    FAIL {
        // - the server is not responding, or responds with HTTP code, such as 500
    };

    /**
     * @return the exact Enum used to create that enum which can be used
     *         to check against provided string.
     *         Throws an IllegalArgumentException if no match is found.
     */
    public static ServerState getEnumFromString(String s) {
        if (OK.name().equals(s)) {
            return OK;
        } else if (WARN.name().equals(s)) {
            return WARN;
        } else if (FAIL.name().equals(s)) {
            return FAIL;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }

    /**
     * @return the exact String used to create that enum which can be used
     *         to check against provided string.
     *         Throws an IllegalArgumentException if no match is found.
     */
    public static String getString(ServerState state) {
        if (ServerState.OK.equals(state)) {
            return "OK";
        } else if (ServerState.WARN.equals(state)) {
            return "WARN";
        } else if (ServerState.FAIL.equals(state)) {
            return "FAIL";
        }
        throw new IllegalArgumentException("No String specified for existing Enum");
    }
}
