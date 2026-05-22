public class CassandraEventStore implements EventStore {

    private final EventStoreDao eventStoreDao;

    @Inject
    public CassandraEventStore(EventStoreDao eventStoreDao) {
        this.eventStoreDao = eventStoreDao;
    }

    @Override
    public void appendAll(List<Event> events) {
        if (events.isEmpty()) {
            return;
        }
        doAppendAll(events);
    }

    public void doAppendAll(List<Event> events) {
        Preconditions.checkArgument(Event.belongsToSameAggregate(events));

        boolean success = eventStoreDao.appendAll(events).block();
        if (!success) {
            throw new EventStoreFailedException("Concurrent update to the EventStore detected");
        }
    }

    @Override
    public History getEventsOfAggregate(AggregateId aggregateId) {
        return eventStoreDao.getEventsOfAggregate(aggregateId);
    }
}
