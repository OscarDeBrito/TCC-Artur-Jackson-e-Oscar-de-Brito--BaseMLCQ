public class HadoopMapperUtils {
    /** Thread-local mapper index. */
    private static final ThreadLocal<Integer> MAP_IDX = new ThreadLocal<>();

    /**
     * @return Current mapper index.
     */
    public static int mapperIndex() {
        Integer res = MAP_IDX.get();

        return res != null ? res : -1;
    }

    /**
     * @param idx Current mapper index.
     */
    public static void mapperIndex(Integer idx) {
        MAP_IDX.set(idx);
    }

    /**
     * Clear mapper index.
     */
    public static void clearMapperIndex() {
        MAP_IDX.remove();
    }

    /**
     * Constructor.
     */
    private HadoopMapperUtils() {
        // No-op.
    }
}
