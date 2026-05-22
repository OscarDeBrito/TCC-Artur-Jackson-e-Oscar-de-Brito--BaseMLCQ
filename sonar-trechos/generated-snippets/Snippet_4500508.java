public class Snippet__4500508 {

    private Object getKey(Event e) {
        // Write the data to HDFS
        String timestamp = e.getHeaders().get("timestamp");
        long eventStamp;

        if (timestamp == null) {
          eventStamp = System.currentTimeMillis();
        } else {
          eventStamp = Long.valueOf(timestamp);
        }
        return new LongWritable(eventStamp);
      }

}
