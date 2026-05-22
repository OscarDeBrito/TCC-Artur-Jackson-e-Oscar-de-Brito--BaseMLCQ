public class CookieUtils {

  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private static final String THEME_PARAMETER = "tobago.theme";

  private static final int ONE_YEAR_IN_SECONDS = 365 * 24 * 60 * 60;

  private CookieUtils() {
  }

  public static String getThemeNameFromCookie(final HttpServletRequest request) {
    String themeName = null;
    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (final Cookie cookie : cookies) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("cookie name  ='{}'", cookie.getName());
          LOG.debug("cookie value ='{}'", cookie.getValue());
          LOG.debug("cookie path  ='{}'", cookie.getPath());
        }
        if (THEME_PARAMETER.equals(cookie.getName())) {
          themeName = cookie.getValue();
          if (LOG.isDebugEnabled()) {
            LOG.debug("theme from cookie {}='{}'", THEME_PARAMETER, themeName);
          }
          break;
        }
      }
    }
    return themeName;
  }

  public static void setThemeNameToCookie(
      final HttpServletRequest request, final HttpServletResponse response, final String themeName) {

    String path = request.getContextPath();
    path = StringUtils.isBlank(path) ? "/" : path;
    boolean found = false;
    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (final Cookie cookie : cookies) {
        if (THEME_PARAMETER.equals(cookie.getName())) {
          if (found) {
            if (LOG.isDebugEnabled()) {
              LOG.debug("Found more than one cookie {}, try to remove them...", THEME_PARAMETER);
            }
            cookie.setMaxAge(0);
          } else {
            found = true;
            if (StringUtils.notEquals(cookie.getValue(), themeName)) {
              if (LOG.isDebugEnabled()) {
                LOG.debug("update theme {} -> {}", cookie.getValue(), themeName);
              }
              cookie.setValue(themeName);
            }
            if (StringUtils.notEquals(cookie.getPath(), path)) {
              if (LOG.isDebugEnabled()) {
                LOG.debug("update path  {} -> {}", cookie.getPath(), path);
              }
              cookie.setPath(path);
            }
            cookie.setMaxAge(ONE_YEAR_IN_SECONDS);
          }
          response.addCookie(cookie);
        }
      }
    }
    if (!found) {
      final Cookie cookie = new Cookie(THEME_PARAMETER, themeName);
      cookie.setPath(path);
      cookie.setMaxAge(ONE_YEAR_IN_SECONDS);
      response.addCookie(cookie);
    }
  }

  public static void removeThemeNameCookie(
      final HttpServletRequest request, final HttpServletResponse response) {

    String path = request.getContextPath();
    path = StringUtils.isBlank(path) ? "/" : path;
    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (final Cookie cookie : cookies) {
        if (THEME_PARAMETER.equals(cookie.getName())) {
          cookie.setMaxAge(0);
          cookie.setValue(null);
          response.addCookie(cookie);
        }
      }
    }
  }
}
