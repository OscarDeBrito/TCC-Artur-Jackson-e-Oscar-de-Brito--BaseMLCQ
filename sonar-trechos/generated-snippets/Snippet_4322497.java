public class Snippet__4322497 {

    public Connection getConnection() {
    		Connection conn = null;
    		String jdbcurl = "";
    		try {
    			NsTest.logger.println(getThread_id()
    					+ " is getting a connection to the database...");

    			if (NsTest.embeddedMode) {
    				jdbcurl = NsTest.embedDbURL + ";" + NsTest.bootPwd;
    			} else {
    				if (NsTest.driver_type.equalsIgnoreCase("DerbyClient"))
    					jdbcurl = NsTest.clientDbURL + ";" + NsTest.bootPwd;

    			}
    			NsTest.logger.println("-->Thread " + getThread_id()
    					+ " starting with url " + jdbcurl + " <--");
    			conn = DriverManager.getConnection(jdbcurl, NsTest.prop);
    		} catch (Exception e) {
                e.printStackTrace( NsTest.logger );
    			NsTest.logger.println("FAIL: " + getThread_id()
    					+ " could not get the database connection");
    			printException("Failed getting database connection using "
    					+ jdbcurl, e);
    		}
    		// for statistical purposes, add one to the num of connections makde
    		NsTest.addStats(NsTest.CONNECTIONS_MADE, 1);
    		NsTest.logger.println("Connection number: " + NsTest.numConnections);
    		return conn; // null if there was a problem, else a valid connection
    	}

}
