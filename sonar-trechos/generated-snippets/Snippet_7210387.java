public class Snippet__7210387 {

    public void severe(String msg, Object... params) {
        if (isSevereEnabled()) {
          CallerDetails details = inferCaller();
          logger.logp(Level.SEVERE, details.clazz, details.method, msg, params);
        }
      }

}
