public class RecomputePomDiagnosticsCommand {
  /**
   * Recomputes diagnostics for the pom.xml.
   *
   * @param arguments contains uri of pom.xml
   * @param pm progress monitor
   * @return true if diagnostics were published otherwise returns false
   */
  public static Boolean execute(List<Object> arguments, IProgressMonitor pm) {
    Preconditions.checkArgument(arguments.size() >= 1, "Resource uri is expected");

    ensureNotCancelled(pm);

    final String fileUri = (String) arguments.get(0);

    PublishDiagnosticsParams diagnostics = computeDiagnostics(fileUri);
    if (diagnostics == null) {
      return false;
    }

    JavaLanguageServerPlugin.getInstance().getClientConnection().publishDiagnostics(diagnostics);

    return true;
  }

  static PublishDiagnosticsParams computeDiagnostics(String fileUri) {
    IFile file = JDTUtils.findFile(fileUri);
    if (file == null) {
      return null;
    }

    IMarker[] markers = null;

    try {
      markers = file.findMarkers(null, true, 1);
    } catch (CoreException e) {
      JavaLanguageServerPlugin.logException("Can't find markers for: " + fileUri, e);
    }
    IDocument document = JsonRpcHelpers.toDocument(file);

    return new PublishDiagnosticsParams(
        ResourceUtils.toClientUri(fileUri), toDiagnosticsArray(document, markers));
  }
}
