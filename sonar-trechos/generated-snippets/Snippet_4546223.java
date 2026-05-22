public class Snippet__4546223 {

    @Override
        public void stopScheduler(final String name) {
            final Scheduler scheduler = this.schedulers.remove(name);
            try {
                scheduler.shutdown();
            } catch (final SchedulerException e) {
                logger.error(e.getMessage(), e);
            }
        }

}
