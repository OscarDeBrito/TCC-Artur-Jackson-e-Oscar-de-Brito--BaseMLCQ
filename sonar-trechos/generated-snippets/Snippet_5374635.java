public class Snippet__5374635 {

    @SuppressWarnings("rawtypes")
        protected void incSpillCount(Enum counter, long numRecsSpilled) {
            PigStatusReporter reporter = PigStatusReporter.getInstance();
            if (reporter != null && reporter.getCounter(counter)!=null) {
                reporter.getCounter(counter).increment(numRecsSpilled);
            } else {
                PigHadoopLogger.getInstance().warn(mContents, "Spill counter incremented", counter);
            }
        }

}
