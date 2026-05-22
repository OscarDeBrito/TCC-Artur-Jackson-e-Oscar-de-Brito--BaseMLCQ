public class Snippet__9442366 {

    private static void setFieldValue(Field field, String value) {
            try {
                field.set(null, value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new Error("Unable to access or set message for field " + field.getName());
            }
        }

}
