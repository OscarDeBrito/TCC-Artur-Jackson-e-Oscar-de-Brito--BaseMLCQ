public class Snippet__7465317 {

    @SuppressWarnings("unused")
        public static boolean test(int i, int time) throws InterruptedException {
            waitTime = time;
            final Thread thread = new Thread();
            synchronized (thread) {
                // start the thread and wait for it
                thread.setDaemon(true); // in case the thread gets stuck
                thread.start();
                while (!thread.wait1Condition) {
                    thread.wait(10000);
                }
            }
            synchronized (start) {
                thread.interrupt();
                thread.sentInterrupt = true;
            }
            synchronized (end) {
                while (!thread.wait2Condition) {
                    end.wait(10000);
                }
            }
            return thread.interrupted;
        }

}
