public interface EventQueue {

    public class EventQueueElement implements Serializable {

        private static final long serialVersionUID = 1L;
        Event event;

        public EventQueueElement(Event e) {
            event = e;
        }
    }

    /**
     * Initialize the event queue
     * @param conf configuration
     */
    void init(Configuration conf);

    /**
     * Add event to queue
     * @param e event
     */
    void add(Event e);

    /**
     * Fetch events from queue in batch
     * @return events set
     */
    List<Event> pollBatch();

    /**
    * Fetch single event from queue
    * @return event
    */
   Event poll();

    /**
     * Find out if queue is empty
     * @return boolean
     */
    boolean isEmpty();

    /**
     * Get current queue size
     * @return size
     */
    int size();

    /**
     * Read topmost event from queue but do not pop from it
     * @return event
     */
    Event peek();

    /**
     * Get the batch size used during polling events
     * @return batchSize
     */
    int getBatchSize();

    /**
     * Clear the events queue
     */
    void clear();

}
