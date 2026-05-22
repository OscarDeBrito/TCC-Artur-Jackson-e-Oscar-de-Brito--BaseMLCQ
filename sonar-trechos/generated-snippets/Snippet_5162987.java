private class JobRunner implements Runnable {

        private final AbstractExecutable executable;

        public JobRunner(AbstractExecutable executable) {
            this.executable = executable;
        }

        @Override
        public void run() {
            try (SetThreadName ignored = new SetThreadName("Scheduler %s Job %s",
                    System.identityHashCode(DistributedScheduler.this), executable.getId())) {
                if (jobLock.lock(getLockPath(executable.getId()))) {
                    logger.info(executable.toString() + " scheduled in server: " + serverName);

                    context.addRunningJob(executable);
                    jobWithLocks.add(executable.getId());
                    executable.execute(context);
                }
            } catch (ExecuteException e) {
                logger.error("ExecuteException job:" + executable.getId() + " in server: " + serverName, e);
            } catch (Exception e) {
                logger.error("unknown error execute job:" + executable.getId() + " in server: " + serverName, e);
            } finally {
                context.removeRunningJob(executable);
                releaseJobLock(executable);
                // trigger the next step asap
                fetcherPool.schedule(fetcher, 0, TimeUnit.SECONDS);
            }
        }

        //release job lock when job state is ready or running and the job server keep the cube lock.
        private void releaseJobLock(AbstractExecutable executable) {
            if (executable instanceof DefaultChainedExecutable) {
                ExecutableState state = executable.getStatus();

                if (state != ExecutableState.READY && state != ExecutableState.RUNNING) {
                    if (jobWithLocks.contains(executable.getId())) {
                        logger.info(
                                executable.toString() + " will release the lock for the job: " + executable.getId());
                        jobLock.unlock(getLockPath(executable.getId()));
                        jobWithLocks.remove(executable.getId());
                    }
                }
            }
        }
    }
