public interface HttpServer {

    /**
     * Http Status Code.
     */
    enum StatusCode {
        OK(200),
        REDIRECT(302),
        FORBIDDEN(403),
        NOT_FOUND(404),
        INTERNAL_ERROR(500);

        private int value;

        StatusCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Http Request Method.
     */
    enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    /**
     * Http ApiTypes.
     */
    enum ApiType {
        HEARTBEAT,
        SERVER_CONFIG,
        METRICS,

        // ledger
        DELETE_LEDGER,
        LIST_LEDGER,
        GET_LEDGER_META,
        READ_LEDGER_ENTRY,
        // bookie
        LIST_BOOKIES,
        LIST_BOOKIE_INFO,
        LAST_LOG_MARK,
        LIST_DISK_FILE,
        EXPAND_STORAGE,
        GC,
        GC_DETAILS,

        // autorecovery
        RECOVERY_BOOKIE,
        LIST_UNDER_REPLICATED_LEDGER,
        WHO_IS_AUDITOR,
        TRIGGER_AUDIT,
        LOST_BOOKIE_RECOVERY_DELAY,
        DECOMMISSION
    }

    /**
     * Initialize the HTTP server with underline service provider.
     */
    void initialize(HttpServiceProvider httpServiceProvider);

    /**
     * Start the HTTP server on given port.
     */
    boolean startServer(int port);

    /**
     * Stop the HTTP server.
     */
    void stopServer();

    /**
     * Check whether the HTTP server is still running.
     */
    boolean isRunning();
}
