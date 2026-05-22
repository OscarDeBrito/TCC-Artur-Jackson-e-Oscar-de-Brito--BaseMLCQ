public final class VM {
    private static final String valueSeparator = "\t";
    private static final String versionValue = getVersionValue();

    @Platforms(Platform.HOSTED_ONLY.class)
    private static String getVersionValue() {
        String version = System.getProperty("org.graalvm.version");
        VMError.guarantee(version != null);
        version = VM.class.getName() + valueSeparator + "GraalVM " + version;
        String config = System.getProperty("org.graalvm.config", "");
        if (!config.isEmpty()) {
            version += " " + config;
        }
        return version;
    }

    private static final String VERSION_INFO_SYMBOL_NAME = "__svm_version_info";

    private static final CGlobalData<CCharPointer> VERSION_INFO = CGlobalDataFactory.createCString(versionValue, VERSION_INFO_SYMBOL_NAME);

    public static String getVersion() {
        try {
            CCharPointer versionInfoBytes = VERSION_INFO.get();
            ByteBuffer buffer = CTypeConversion.asByteBuffer(versionInfoBytes, Math.toIntExact(SubstrateUtil.strlen(versionInfoBytes).rawValue()));
            String version = Utf8.utf8ToString(true, buffer);
            VMError.guarantee(versionValue.equals(version), "Version info mismatch: " + VERSION_INFO_SYMBOL_NAME + " contains " + version + " (expected " + versionValue + ")");
            return SubstrateUtil.split(version, valueSeparator)[1];
        } catch (CharConversionException ignore) {
            throw VMError.shouldNotReachHere("Invalid version info in " + VERSION_INFO_SYMBOL_NAME);
        }
    }
}
