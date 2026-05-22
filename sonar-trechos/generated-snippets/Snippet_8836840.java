public class DatabaseTestCases {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseTestCases.class);

    protected static String hostAddress = "localhost";
    protected static int port = 20000;
    protected static String userName = "admin";
    protected static String password = "admin";
    protected static String driver = "org.apache.derby.jdbc.ClientDriver";

    public static String getHostAddress() {
        return hostAddress;
    }

    public static int getPort() {
        return port;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getJDBCUrl() {
        return new StringBuilder().append("jdbc:derby://").append(getHostAddress()).append(":").append(getPort())
                .append("/experiment_catalog;create=true;user=").append(getUserName()).append(";password=")
                .append(getPassword()).toString();
    }

    public static void waitTillServerStarts() {
        DBUtil dbUtil = null;

        try {
            dbUtil = new DBUtil(getJDBCUrl(), getUserName(), getPassword(), getDriver());
        } catch (Exception e) {
           // ignore
        }

        Connection connection = null;
        try {
            if (dbUtil != null) {
                connection = dbUtil.getConnection();
            }
        } catch (Throwable e) {
            // ignore
        }

        while (connection == null) {
            try {
                Thread.sleep(1000);
                try {
                    if (dbUtil != null) {
                        connection = dbUtil.getConnection();
                    }
                } catch (SQLException e) {
                    // ignore
                }
            } catch (InterruptedException e) {
                // ignore
            }
        }

    }

    public static void executeSQL(String sql) throws Exception {
        DBUtil dbUtil = new DBUtil(getJDBCUrl(), getUserName(), getPassword(), getDriver());
        dbUtil.executeSQL(sql);
    }

    public DBUtil getDbUtil () throws Exception {
        return new DBUtil(getJDBCUrl(), getUserName(), getPassword(), getDriver());

    }

    public Connection getConnection() throws Exception {

        DBUtil dbUtil =  getDbUtil ();
        Connection connection = dbUtil.getConnection();
        connection.setAutoCommit(true);
        return connection;

    }

}
