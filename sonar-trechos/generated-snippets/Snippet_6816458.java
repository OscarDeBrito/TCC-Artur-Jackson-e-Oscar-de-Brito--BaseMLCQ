public class Snippet__6816458 {

    private void checkBasicCall(CallBasicNode node, TemplateRegistry registry) {
        TemplateMetadata callee = registry.getBasicTemplateOrElement(node.getCalleeName());

        if (callee == null) {
          String extraErrorMessage =
              SoyErrors.getDidYouMeanMessage(
                  registry.getBasicTemplateOrElementNames(), node.getCalleeName());
          errorReporter.report(
              node.getSourceLocation(),
              CALL_TO_UNDEFINED_TEMPLATE,
              node.getCalleeName(),
              extraErrorMessage);
        } else {
          SoyFileKind calleeKind = callee.getSoyFileKind();
          String callerFilePath = node.getSourceLocation().getFilePath();
          String calleeFilePath = callee.getSourceLocation().getFilePath();
          if (calleeKind == SoyFileKind.INDIRECT_DEP) {
            errorReporter.report(
                node.getSourceLocation(),
                CALL_TO_INDIRECT_DEPENDENCY,
                calleeFilePath);
          }
        }
      }

}
