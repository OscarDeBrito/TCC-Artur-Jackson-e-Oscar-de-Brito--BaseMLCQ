public class ThreadTimings {
    private static ThreadMXBean bean;

    private static void checkBean() {
        if (bean == null) {
            bean = ManagementFactory.getThreadMXBean();
        }
    }

    public static long[] userSysTimeInNanos() {
        if (!FastRConfig.UseMXBeans) {
            return null;
        }
        checkBean();
        long userTimeInNanos = bean.getCurrentThreadUserTime();
        return new long[]{userTimeInNanos, bean.getCurrentThreadCpuTime() - userTimeInNanos};
    }
}
