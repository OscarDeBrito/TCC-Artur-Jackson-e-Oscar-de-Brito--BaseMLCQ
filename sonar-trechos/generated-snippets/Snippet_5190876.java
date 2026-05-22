public class GitPgmCommand extends AbstractGitCommand {
    /**
     * @param rootDirResolver Resolver for GIT root directory
     * @param command Command to execute
     * @param executorService An {@link CloseableExecutorService} to be used when {@link #start(Environment)}-ing
     * execution. If {@code null} an ad-hoc single-threaded service is created and used.
     */
    public GitPgmCommand(GitLocationResolver rootDirResolver, String command, CloseableExecutorService executorService) {
        super(rootDirResolver, command, executorService);
    }

    @Override
    public void run() {
        String command = getCommand();
        OutputStream err = getErrorStream();
        try {
            List<String> strs = parseDelimitedString(command, " ", true);
            String[] args = strs.toArray(new String[strs.size()]);
            for (int i = 0; i < args.length; i++) {
                String argVal = args[i];
                if (argVal.startsWith("'") && argVal.endsWith("'")) {
                    args[i] = argVal.substring(1, argVal.length() - 1);
                    argVal = args[i];
                }

                if (argVal.startsWith("\"") && argVal.endsWith("\"")) {
                    args[i] = argVal.substring(1, argVal.length() - 1);
                    argVal = args[i];
                }
            }

            GitLocationResolver resolver = getGitLocationResolver();
            Path rootDir = resolver.resolveRootDirectory(command, args, getServerSession(), getFileSystem());
            ValidateUtils.checkState(rootDir != null, "No root directory provided for %s command", command);

            new EmbeddedCommandRunner(rootDir).execute(args, getInputStream(), getOutputStream(), err);
            onExit(0);
        } catch (Throwable t) {
            try {
                err.write((t.getMessage() + "\n").getBytes(StandardCharsets.UTF_8));
                err.flush();
            } catch (IOException e) {
                log.warn("Failed {} to flush command={} failure: {}",
                    e.getClass().getSimpleName(), command, e.getMessage());
            }
            onExit(-1, t.getMessage());
        }
    }
}
