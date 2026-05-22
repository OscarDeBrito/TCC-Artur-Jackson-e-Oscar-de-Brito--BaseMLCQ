public class Snippet__3730280 {

    public void start() {
            checkInst();

            if (scheduler == null) {
                scheduler = new ScheduledThreadPoolExecutor(schedulerThreadSize);
            }

            scheduler.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    collectSql();
                }
            }, timeBetweenSqlCollect, timeBetweenSqlCollect, timeUnit);

            scheduler.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    collectSpringMethod();
                }
            }, timeBetweenSpringCollect, timeBetweenSpringCollect, timeUnit);

            scheduler.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    collectWebURI();
                }
            }, timeBetweenWebUriCollect, timeBetweenWebUriCollect, timeUnit);
        }

}
