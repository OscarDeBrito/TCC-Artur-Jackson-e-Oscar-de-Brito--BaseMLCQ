public class Snippet__5111196 {

    public void finishUp() throws InterruptedException, IOException {
          join();
          Throwable thr = exception;
          if (thr != null) {
            if (thr instanceof IOException) {
              throw (IOException) thr;
            } else if (thr instanceof RuntimeException) {
              throw (RuntimeException) thr;
            } else {
              throw (Error) thr;
            }
          }
        }

}
