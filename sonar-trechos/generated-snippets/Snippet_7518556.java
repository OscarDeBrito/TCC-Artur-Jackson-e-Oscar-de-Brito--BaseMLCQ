public class Snippet__7518556 {

    public static boolean isDeprecated(TypeMirror baseType) {
            if (baseType instanceof DeclaredType) {
                return isDeprecated((TypeElement) ((DeclaredType) baseType).asElement());
            }
            return false;
        }

}
