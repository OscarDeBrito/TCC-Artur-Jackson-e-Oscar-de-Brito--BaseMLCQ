public class LaunchCommandHandler {

    /**
     * Get the approximate command line length based on the launch arguments.
     * @param launchArguments - the launch arguments
     * @return the approximate command line length
     */
    public static int getLaunchCommandLength(LaunchArguments launchArguments) {
        String encoding = StringUtils.isBlank(launchArguments.encoding) ? StandardCharsets.UTF_8.name() : launchArguments.encoding;
        launchArguments.vmArgs += String.format(" -Dfile.encoding=%s", encoding);
        String address = launchArguments.noDebug ? "" : "888888";
        String[] commands = LaunchRequestHandler.constructLaunchCommands(launchArguments, false, address);
        int cwdLength = launchArguments.console == CONSOLE.internalConsole ? 0 : StringUtils.length("cd " + launchArguments.cwd + " && ");
        return cwdLength + String.join(" ", commands).length();
    }

}
