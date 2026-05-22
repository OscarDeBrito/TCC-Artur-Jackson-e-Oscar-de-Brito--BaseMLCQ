public class Snippet__8956394 {

    private static final class DataLossLogger {
        private static final Map<String, Integer> msgCount = new HashMap<String, Integer>();
        private static String getColumnTypeKey(HCatFieldSchema fieldSchema) {
          return fieldSchema.getName() + "_" + (fieldSchema.getTypeInfo() == null ?
            fieldSchema.getType() : fieldSchema.getTypeInfo());
        }
        private void logDataLossMsg(HCatFieldSchema fieldSchema, Object pigOjb, String msg) {
          String key = getColumnTypeKey(fieldSchema);
          if(!msgCount.containsKey(key)) {
            msgCount.put(key, 0);
            LOG.warn(msg + " " + "Will write NULL instead.  Only 1 such message per type/column is emitted.");
          }
          msgCount.put(key, msgCount.get(key) + 1);
        }
      }

}
