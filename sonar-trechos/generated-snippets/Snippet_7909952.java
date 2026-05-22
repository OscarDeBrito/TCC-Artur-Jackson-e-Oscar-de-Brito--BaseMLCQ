public class Snippet__7909952 {

    public static SourcePath createEmptyInitModule(
          BuildTarget buildTarget,
          ProjectFilesystem projectFilesystem,
          ActionGraphBuilder graphBuilder) {
        BuildTarget emptyInitTarget = getEmptyInitTarget(buildTarget);
        Path emptyInitPath =
            BuildTargetPaths.getGenPath(projectFilesystem, buildTarget, "%s/__init__.py");
        WriteFile rule =
            graphBuilder.addToIndex(
                new WriteFile(
                    emptyInitTarget, projectFilesystem, "", emptyInitPath, /* executable */ false));
        return rule.getSourcePathToOutput();
      }

}
