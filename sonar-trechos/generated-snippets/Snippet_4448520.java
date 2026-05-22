public class Snippet__4448520 {

    @Override
        public void output() {
          if (work.obj != null) {
            com.clearspring.analytics.stream.cardinality.HyperLogLog hll =
                    (com.clearspring.analytics.stream.cardinality.HyperLogLog) work.obj;

            try {
              byte[] ba = hll.getBytes();
              out.buffer = buffer.reallocIfNeeded(ba.length);
              out.start = 0;
              out.end = ba.length;
              out.buffer.setBytes(0, ba);
              out.isSet = 1;
            } catch (java.io.IOException e) {
              throw new org.apache.drill.common.exceptions.DrillRuntimeException("Failed to get HyperLogLog output", e);
            }
          } else {
            out.isSet = 0;
          }
        }

}
