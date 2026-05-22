public class Snippet__8766133 {

    @SuppressWarnings("unchecked")
      private static Class<? extends Principal> getOsPrincipalClass() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
          String principalClass = null;
          if (IBM_JAVA) {
            if (is64Bit) {
              principalClass = "com.ibm.security.auth.UsernamePrincipal";
            } else {
              if (windows) {
                principalClass = "com.ibm.security.auth.NTUserPrincipal";
              } else if (aix) {
                principalClass = "com.ibm.security.auth.AIXPrincipal";
              } else {
                principalClass = "com.ibm.security.auth.LinuxPrincipal";
              }
            }
          } else {
            principalClass = windows ? "com.sun.security.auth.NTUserPrincipal"
                : "com.sun.security.auth.UnixPrincipal";
          }
          return (Class<? extends Principal>) cl.loadClass(principalClass);
        } catch (ClassNotFoundException e) {
          LOG.error("Unable to find JAAS classes:" + e.getMessage());
        }
        return null;
      }

}
