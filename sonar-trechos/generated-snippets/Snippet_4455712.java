public class Snippet__4455712 {

    @SuppressWarnings("unused")
      public void setTimestamp(String field, String value) {
        if (value != null) {
          //Convert the date string into a long
          long ts = 0;
          try {
            Date d = this.dateFormatter.parse(value);
            ts = d.getTime();
          } catch (Exception e) {
            //If the date formatter does not successfully create a date, the timestamp will fall back to zero
            //Do not throw exception
          }
          final TimeStampWriter tw = times.get(field);
          if (tw != null) {
            LOG.trace("Parsed field: {}, as time: {}", field, value);
            tw.writeTimeStamp(ts);
          } else {
            LOG.warn("No 'timestamp' writer found for field: {}", field);
          }
        }
      }

}
