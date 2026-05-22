static class DelayedTimer implements Delayed {
        // most of it copied from
        // java.util.concurrent.ScheduledThreadPoolExecutor

        /**
         * Sequence number to break scheduling ties, and in turn to
         * guarantee FIFO order among tied entries.
         */
        private static final AtomicLong sequencer = new AtomicLong(0);

        /** Sequence number to break ties FIFO */
        private final long sequenceNumber;


        /** The time the task is enabled to execute in nanoTime units */
        private volatile long time;

        private final Timer timer;

        DelayedTimer(Timer timer, long nanos) {
            this.timer = timer;
            time = nanos;
            sequenceNumber = sequencer.getAndIncrement();
        }


        public final long getDelay(TimeUnit unit) {
            return  unit.convert(time - now(), TimeUnit.NANOSECONDS);
        }

        final void setTime(long nanos) {
            time = nanos;
        }

        final Timer getTimer() {
            return timer;
        }

        public int compareTo(Delayed other) {
            if (other == this) { // compare zero ONLY if same object
                return 0;
            }
            if (other instanceof DelayedTimer) {
                DelayedTimer x = (DelayedTimer)other;
                long diff = time - x.time;
                if (diff < 0) {
                    return -1;
                } else if (diff > 0) {
                    return 1;
                } else if (sequenceNumber < x.sequenceNumber) {
                    return -1;
                }  else {
                    return 1;
                }
            }
            long d = (getDelay(TimeUnit.NANOSECONDS) -
                      other.getDelay(TimeUnit.NANOSECONDS));
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
        }
    }
