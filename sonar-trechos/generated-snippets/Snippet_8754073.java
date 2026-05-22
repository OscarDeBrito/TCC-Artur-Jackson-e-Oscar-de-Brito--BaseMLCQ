public class Snippet__8754073 {

    private Dest resolveAction(WebApp.HTTP method, Dest dest, String path) {
        if (dest.prefix.length() == 1) {
          return null;
        }
        checkState(!isGoodMatch(dest, path), dest.prefix);
        checkState(SLASH.countIn(path) > 1, path);
        List<String> parts = WebApp.parseRoute(path);
        String controller = parts.get(WebApp.R_CONTROLLER);
        String action = parts.get(WebApp.R_ACTION);
        return add(method, pjoin("", controller, action), dest.controllerClass,
                   action, null);
      }

}
