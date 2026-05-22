public class Snippet__4364314 {

    public void finer(String msg) {
            if (isLoggable(Level.FINER)) {
                LogRecord lr = new LogRecord(Level.FINER, msg);
                doLog(lr);
            }
        }

}
