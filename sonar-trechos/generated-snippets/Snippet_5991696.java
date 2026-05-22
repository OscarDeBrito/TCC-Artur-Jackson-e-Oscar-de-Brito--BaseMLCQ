public class Snippet__5991696 {

    public void info(Supplier<String> msg) {
            if (log.isInfoEnabled()) {
                log.info(msg.get());
            }
        }

}
