public class Snippet__7273013 {

    private String dumpStack(ThreadInfo ti) {
            StringBuilder buffer = new StringBuilder();
            buffer.append("*** Thread stack dump:\n"); // NOI18N

            for (int i = ti.stackTopIdx; i >= 0; i--) {
                DebugInfoCollector collector = new DebugInfoCollector();
                TimedCPUCCTNode frame = ti.stack[i];
                RuntimeCCTNodeProcessor.process(frame, collector);
                buffer.append(collector.getInfo(frame)).append('\n'); // NOI18N
            }

            return buffer.toString();
        }

}
