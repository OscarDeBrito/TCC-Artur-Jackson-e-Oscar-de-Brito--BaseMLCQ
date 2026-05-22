public class Snippet__5389735 {

    public static boolean isParent(final ClassLoader supposedParent, final ClassLoader supposedChild) {
            ClassLoader parent = supposedChild;
            while (parent != null) {
                if (parent == supposedParent) {
                    return true;
                }
                parent = parent.getParent();
            }

            return false;
        }

}
