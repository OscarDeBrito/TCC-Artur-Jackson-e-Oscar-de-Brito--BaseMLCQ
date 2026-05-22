public interface GitLocationResolver {
    /**
     * @param command The complete received command
     * @param args The command split into arguments - {@code args[0]} is the
     * &quot;pure&quot; command itself without any other arguments. <B>Note:</B>
     * changing the content of the arguments array may affect command execution
     * in undetermined ways, due to invocation code changes without prior notice,
     * so <U>highly recommended to avoid it</U>.
     * @param session The {@link ServerSession} through which the command was received
     * @param fs The {@link FileSystem} associated with the server session
     * @return The local GIT repository root path
     * @throws IOException If failed to resolve
     */
    Path resolveRootDirectory(String command, String[] args, ServerSession session, FileSystem fs) throws IOException;

    /**
     * Creates a resolver that returns the same root directory for any invocation of
     * {@link #resolveRootDirectory(String, String[], ServerSession, FileSystem) resolveRootDirectory}
     *
     * @param rootDir The (never {@code null}) root directory to return
     * @return The wrapper resolver
     */
    static GitLocationResolver constantPath(Path rootDir) {
        Objects.requireNonNull(rootDir, "No root directory provided");
        return (cmd, args, session, fs) -> rootDir;
    }
}
