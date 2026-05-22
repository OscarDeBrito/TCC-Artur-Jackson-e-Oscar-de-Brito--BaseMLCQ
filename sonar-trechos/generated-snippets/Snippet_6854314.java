public class Snippet__6854314 {

    public static String getZynamicsDirectory(final String companyName) {
        // Use lowercase for the company name, since that is customary on Linux. For "zynamics", this
        // is a no-op, but it will do the right thing for "Google".
        return SystemHelpers.getApplicationDataDirectory() + File.separator
            + (SystemHelpers.isRunningWindows() ? companyName : ("." + companyName.toLowerCase()))
            + File.separator;
      }

}
