public class Snippet__4116762 {

    private void onContainerAdded(Entity container, boolean rebalanceNow) {
            subscriptions().subscribe(container, Attributes.LOCATION_CHANGED, eventHandler);
            Location location = locationFinder.apply(container);
        
            if (LOG.isTraceEnabled()) LOG.trace("{} recording addition of container {} in location {}", new Object[] {this, container, location});
            model.onContainerAdded(container, location);
        
            if (rebalanceNow) scheduleLatencyReductionJig();
        }

}
