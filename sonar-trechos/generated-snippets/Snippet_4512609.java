public class Snippet__4512609 {

    public void addBundleListener(BundleListener l)
        {
            checkValidity();

            // CONCURRENCY NOTE: This is a check-then-act situation, but
            // internally the event dispatcher double checks whether or not
            // the bundle context is valid before adding the service listener
            // while holding the event queue lock, so it will either succeed
            // or fail.

            Object sm = System.getSecurityManager();

            if (sm != null)
            {
                if (l instanceof SynchronousBundleListener)
                {
                    ((SecurityManager) sm).checkPermission(new AdminPermission(m_bundle,
                        AdminPermission.LISTENER));
                }
            }

            m_felix.addBundleListener(m_bundle, l);
        }

}
