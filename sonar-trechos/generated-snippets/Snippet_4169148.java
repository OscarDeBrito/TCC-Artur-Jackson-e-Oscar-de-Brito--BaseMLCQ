public class Snippet__4169148 {

    public void run() {
        try {
          JSONObject result = plugin.execute();
          int status = (Integer) result.get("status");
          if (status < 0) {
            System.out.println("Error");
            log.warn("[ChukwaError]:" + Exec.class + ", "
                + result.get("stderr"));
          } else {
            log.info(result.get("stdout"));
          }
        } catch (Exception e) {
          log.error("Exec output unparsable:" + this.cmde);
        }
      }

}
