public class AnnotationsUtil {
    protected AnnotationsUtil() {
    }

    public static String getElementName(Field field) {
        if (field.isAnnotationPresent(Name.class))
            return field.getAnnotation(Name.class).value();
        if (field.getType().isAnnotationPresent(Name.class))
            return field.getType().getAnnotation(Name.class).value();
        return splitCamelCase(field.getName());
    }

    public static Functions getFunction(Field field) {
        if (field.isAnnotationPresent(OkButton.class))
            return Functions.OK_BUTTON;
        if (field.isAnnotationPresent(CloseButton.class))
            return Functions.CLOSE_BUTTON;
        if (field.isAnnotationPresent(CancelButton.class))
            return Functions.CANCEL_BUTTON;
        return Functions.NONE;
    }

    public static String splitCamelCase(String camel) {
        String result = (camel.charAt(0) + "").toUpperCase();
        for (int i = 1; i < camel.length() - 1; i++)
            result += (isUpperCase(camel.charAt(i)) && (
                    isLowerCase(camel.charAt(i+1)) || isLowerCase(camel.charAt(i-1)))
                    ? " " : "") + camel.charAt(i);
        return result + camel.charAt(camel.length() - 1);
    }

    private static boolean isCapital(char ch) {
        return 'A' < ch && ch < 'Z';
    }

}
