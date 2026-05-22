public class Snippet__8514290 {

    private void init() throws ConfigurationException {
            if (_mshostDao != null)
                return;

            Properties dbProps = DbProperties.getDbProperties();

            _clusterServicePort = NumbersUtil.parseInt(dbProps.getProperty("cluster.servlet.port"), DEFAULT_SERVICE_PORT);
            if (s_logger.isInfoEnabled())
                s_logger.info("Cluster servlet port : " + _clusterServicePort);
        }

}
