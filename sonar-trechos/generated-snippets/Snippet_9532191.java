public class Snippet__9532191 {

    protected String getContainerPrivateDir(String appIdStr,
          String containerIdStr) {
        return getAppPrivateDir(appIdStr) + Path.SEPARATOR + containerIdStr
            + Path.SEPARATOR;
      }

}
