final class ProviderUtil {
    private static volatile boolean initialized = false;

    static void initSunEC() {
        if (initialized) {
            return;
        }
        /* Lazy initialization. */
        initOnce();
    }

    // Checkstyle: stop
    private static synchronized void initOnce() {
        // Checkstyle: resume
        if (!initialized) {
            try {
                System.loadLibrary("sunec");
            } catch (UnsatisfiedLinkError e) {
                /*
                 * SunEC has a mode where it can function without the full ECC implementation when
                 * native library is absent, however, then fewer EC algorithms are available). If
                 * those algorithms are actually used an java.lang.UnsatisfiedLinkError will be
                 * thrown. Just warn the user that the library could not be loaded.
                 */
                Log.log().string("WARNING: The sunec native library, required by the SunEC provider, could not be loaded. " +
                                "This library is usually shipped as part of the JDK and can be found under <JAVA_HOME>/jre/lib/<platform>/libsunec.so. " +
                                "It is loaded at run time via System.loadLibrary(\"sunec\"), the first time services from SunEC are accessed. " +
                                "To use this provider's services the java.library.path system property needs to be set accordingly " +
                                "to point to a location that contains libsunec.so. " +
                                "Note that if java.library.path is not set it defaults to the current working directory.").newline();
            }
            initialized = true;
        }
    }

}
