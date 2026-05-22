public class Snippet__3700134 {

    public Snippet__3700134(String name, boolean daemon){
            this.name = name;
            this.daemon = daemon;
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        }

}
