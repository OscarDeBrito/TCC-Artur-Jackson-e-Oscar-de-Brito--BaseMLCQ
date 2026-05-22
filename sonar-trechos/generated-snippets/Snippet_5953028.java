public class Snippet__5953028 {

    public void process(List<CloudTrailEvent> events) {
            int i = 0;
            for (CloudTrailEvent event : events) {
                validateEvent(event);
                logger.info(String.format("Process event %d : %s", i++, event.getEventData()));
            }
        }

}
