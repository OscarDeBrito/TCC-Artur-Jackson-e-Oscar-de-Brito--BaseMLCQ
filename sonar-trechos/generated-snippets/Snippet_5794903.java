public class VersionUtils
{

    /**
     * Reads a version number from a properties file on the classpath.  These files are generally created by Gradle.  For
     * example, tapestry-core's properties file is <code>META-INF/gradle/org.apache.tapestry/tapestry-core/pom.properties</code>.
     * The Gradle generated properties files include the version and possibly others properties.
     *
     * The resource is located using the Thread's context class loader.
     *
     * @param resourcePath the complete path to the resource, including a leading slash.
     * @return the version number read from the properties file, or "UNKNOWN" if the version number is not present or
     *         the file can not be opened
     */
    public static String readVersionNumber(String resourcePath)
    {
        String result = "UNKNOWN";

        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                resourcePath);


        if (stream != null)
        {
            Properties properties = new Properties();


            try
            {
                stream = new BufferedInputStream(stream);

                properties.load(stream);

                stream.close();
            }
            catch (IOException ex)
            {
                // Just ignore it.
            }

            String version = properties.getProperty("version");

            // Since the file, if it exists, is created by Gradle and will have the key, I can't see
            // how version would EVER be null, unless there's a problem reading the properties.

            if (version != null) result = version;
        }

        return result;
    }
}
