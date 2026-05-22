public class Snippet__5719331 {

    private static Field getLabelField(final String name) {
            try {
                Field f = Label.class.getDeclaredField(name);
                f.setAccessible(true);
                return f;
            } catch (NoSuchFieldException e) {
                return null;
            }
        }

}
