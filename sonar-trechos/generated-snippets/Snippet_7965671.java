public class Snippet__7965671 {

    public Iterable<JvmMember> getMembersToBeCompiled(final JvmDeclaredType type) {
        if (type instanceof JvmEnumerationType) {
          return _getMembersToBeCompiled((JvmEnumerationType)type);
        } else if (type instanceof JvmGenericType) {
          return _getMembersToBeCompiled((JvmGenericType)type);
        } else if (type != null) {
          return _getMembersToBeCompiled(type);
        } else {
          throw new IllegalArgumentException("Unhandled parameter types: " +
            Arrays.<Object>asList(type).toString());
        }
      }

}
