public class Snippet__7254402 {

    public static String fixLibsDirPath(final String libsDirPath, final String args, boolean createTmp) {
            if (createTmp) {
                try {
                    File tmpFile = File.createTempFile(BINARIES_TMP_PREFIX, BINARIES_TMP_EXT);
                    String tmpPath = tmpFile.getAbsolutePath();
                    tmpFile.delete();
                    Runtime.getRuntime().exec(new String[]{"/bin/ln","-s",libsDirPath,tmpPath});    // NOI18N
                    new File(tmpPath).deleteOnExit();
                    return args.replace(libsDirPath,tmpPath);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                return args.replaceAll("agentpath:(.*?)=(.*?),(.*)", "agentpath:\"$1\"=\"$2\",$3");
            }
        }

}
