public class Snippet__5979331 {

    private void convertToMavenProject(final IProject project) throws InterruptedException {
            Job job = new Job("Enable Maven nature.") {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    try {
                        ResolverConfiguration configuration = new ResolverConfiguration();
                        configuration.setResolveWorkspaceProjects(true);
                        configuration.setSelectedProfiles(""); //$NON-NLS-1$

                        final boolean hasMavenNature = project
                                .hasNature(IMavenConstants.NATURE_ID);

                        IProjectConfigurationManager configurationManager = MavenPlugin
                                .getProjectConfigurationManager();

                        configurationManager.enableMavenNature(project,
                                configuration, monitor);

                        if (!hasMavenNature) {
                            configurationManager.updateProjectConfiguration(
                                    project, monitor);
                        }
                    } catch (CoreException ex) {
                        AwsToolkitCore.getDefault().reportException(ex.getMessage(), ex);
                    }
                    return Status.OK_STATUS;
                }
            };
            job.schedule();
            job.join();
        }

}
