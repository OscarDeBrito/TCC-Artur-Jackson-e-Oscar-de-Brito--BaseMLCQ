public class Snippet__5184123 {

    protected Put buildPut(Document update) throws IOException {
        Key k = new Key(update.getGuid(), update.getSensorType());
        Put put = new Put(Key.toBytes(k));
        long ts = update.getTimestamp() == null || update.getTimestamp() == 0 ? System.currentTimeMillis() : update.getTimestamp();
        byte[] columnQualifier = Bytes.toBytes(ts);
        byte[] doc = JSONUtils.INSTANCE.toJSONPretty(update.getDocument());
        put.addColumn(cf, columnQualifier, doc);
        return put;
      }

}
