public class Snippet__7115420 {

    public Iterable<VariableElement> getImplicitPostfixParams(TypeElement type) {
        if (ElementUtil.isEnum(type)) {
          return implicitEnumParams;
        }
        return Collections.emptyList();
      }

}
