@TargetClass(className = "jline.TerminalFactory", onlyWith = JLineFeature.IsEnabled.class)
final class Target_jline_TerminalFactory {

    @SuppressWarnings("unused")
    @Substitute
    public static Terminal create(String ttyDevice) {
        Terminal t;
        try {
            t = new UnixTerminal();
            t.init();
        } catch (Exception e) {
            Log.error("Failed to construct terminal; falling back to UnsupportedTerminal", e);
            t = new UnsupportedTerminal();
        }

        Log.debug("Created Terminal: ", t);

        return t;
    }
}
