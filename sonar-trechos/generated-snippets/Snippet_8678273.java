public class Snippet__8678273 {

    protected final void unregisterMBean() throws IgniteSpiException {
            // Unregister SPI MBean.
            if (spiMBean != null && ignite != null) {
                assert !U.IGNITE_MBEANS_DISABLED;

                MBeanServer jmx = ignite.configuration().getMBeanServer();

                assert jmx != null;

                try {
                    jmx.unregisterMBean(spiMBean);

                    if (log.isDebugEnabled())
                        log.debug("Unregistered SPI MBean: " + spiMBean);
                }
                catch (JMException e) {
                    throw new IgniteSpiException("Failed to unregister SPI MBean: " + spiMBean, e);
                }
            }
        }

}
