public class Snippet__6800764 {

    public static final class NamedCaptureGroup extends RegExpTree {
        final RegExpTree body;
        final String name;

        NamedCaptureGroup(RegExpTree body, String name) {
          this.body = body;
          this.name = name;
        }

        @Override
        public RegExpTree simplify(String flags) {
          return new NamedCaptureGroup(body.simplify(flags), name);
        }

        @Override
        public boolean isCaseSensitive() {
          return body.isCaseSensitive();
        }

        @Override
        public boolean containsAnchor() {
          return body.containsAnchor();
        }

        @Override
        public int numCapturingGroups() {
          return 1 + body.numCapturingGroups();
        }

        @Override
        public ImmutableList<? extends RegExpTree> children() {
          return ImmutableList.of(body);
        }

        @Override
        protected void appendSourceCode(StringBuilder sb) {
          sb.append("(?<");
          sb.append(name);
          sb.append('>');
          body.appendSourceCode(sb);
          sb.append(')');
        }

        @Override
        protected void appendDebugInfo(StringBuilder sb) {
          sb.append(" name=").append(name);
        }

        @Override
        public boolean equals(Object o) {
          return o instanceof NamedCaptureGroup
              && name.equals(((NamedCaptureGroup) o).name)
              && body.equals(((NamedCaptureGroup) o).body);
        }

        @Override
        public int hashCode() {
          return Objects.hashCode(name) ^ body.hashCode();
        }
      }

}
