@BugPattern(
    name = "DeprecatedThreadMethods",
    summary = "Avoid deprecated Thread methods; read the method's javadoc for details.",
    severity = WARNING)
public class DeprecatedThreadMethods extends BugChecker implements MethodInvocationTreeMatcher {

  private static final Pattern METHOD_NAME_REGEX =
      Pattern.compile("stop|countStackFrames|destroy|resume|suspend");

  // Might be overmatching--Thread subclasses could have additional methods with same names
  private static final Matcher<ExpressionTree> DEPRACATED =
      anyOf(
          Matchers.instanceMethod()
              .onDescendantOf("java.lang.Thread")
              .withNameMatching(METHOD_NAME_REGEX));

  @Override
  public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
    return DEPRACATED.matches(tree, state) ? describeMatch(tree) : Description.NO_MATCH;
  }
}
