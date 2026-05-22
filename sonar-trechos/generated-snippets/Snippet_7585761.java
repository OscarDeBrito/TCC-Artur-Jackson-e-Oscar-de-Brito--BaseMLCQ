public class Snippet__7585761 {

    private File getPath(final LogicalPath logicalPath) {
        final Pom pom = pomManagementService.getPomFromModuleName(logicalPath.getModule());
        final File moduleRoot = getModuleRoot(logicalPath.getModule(), pom);
        final String pathRelativeToPom = logicalPath.getPathRelativeToPom(pom);
        return new File(moduleRoot, pathRelativeToPom);
      }

}
