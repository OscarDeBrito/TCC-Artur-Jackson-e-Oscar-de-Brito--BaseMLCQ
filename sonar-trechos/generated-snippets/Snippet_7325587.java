public class FindBuilds {
    public static void main(final String[] args) {
        final TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();

        final IBuildServer buildServer = tpc.getBuildServer();
        final IBuildDefinition def =
            buildServer.getBuildDefinition(SnippetSettings.PROJECT_NAME, SnippetSettings.BUILD_DEFINITION_NAME);
        final IBuildDetailSpec spec = buildServer.createBuildDetailSpec(def);
        spec.setStatus(BuildStatus.ALL);

        System.out.println("***"); //$NON-NLS-1$
        System.out.println("*** Query builds for build definition " + SnippetSettings.BUILD_DEFINITION_NAME); //$NON-NLS-1$
        System.out.println("***"); //$NON-NLS-1$

        final IBuildQueryResult buildsQueryResult = buildServer.queryBuilds(spec);

        System.out.println("Found " + buildsQueryResult.getBuilds().length + " build(s)."); //$NON-NLS-1$ //$NON-NLS-2$

        for (final IBuildDetail build : buildsQueryResult.getBuilds()) {
            displayBuildProperties(buildServer, build);
        }

    }

    private static void displayBuildProperties(final IBuildServer buildServer, final IBuildDetail build) {
        System.out.println("Build "); //$NON-NLS-1$
        System.out.println("\tBuild Number: " + build.getBuildNumber()); //$NON-NLS-1$
        System.out.println("\tURI: " + build.getURI()); //$NON-NLS-1$
        System.out.println("\tStatus: " + buildServer.getDisplayText(build.getStatus())); //$NON-NLS-1$
        System.out.println("\tRequested By: " + build.getRequestedBy()); //$NON-NLS-1$
        System.out.println(""); //$NON-NLS-1$
    }
}
