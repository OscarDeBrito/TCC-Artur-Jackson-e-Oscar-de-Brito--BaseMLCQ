public class Snippet__5760043 {

    private void declareFrameTypes(final int n, final Object[] o) {
            for (int i = 0; i < n; ++i) {
                if (o[i] instanceof Label) {
                    declareLabel((Label) o[i]);
                }
            }
        }

}
