public class Snippet__8756041 {

    boolean isProcessTreeOverLimit(String containerId,
                                      long currentMemUsage,
                                      long curMemUsageOfAgedProcesses,
                                      long vmemLimit) {
        boolean isOverLimit = false;

        if (currentMemUsage > (2 * vmemLimit)) {
          LOG.warn("Process tree for container: " + containerId
              + " running over twice " + "the configured limit. Limit=" + vmemLimit
              + ", current usage = " + currentMemUsage);
          isOverLimit = true;
        } else if (curMemUsageOfAgedProcesses > vmemLimit) {
          LOG.warn("Process tree for container: " + containerId
              + " has processes older than 1 "
              + "iteration running over the configured limit. Limit=" + vmemLimit
              + ", current usage = " + curMemUsageOfAgedProcesses);
          isOverLimit = true;
        }

        return isOverLimit;
      }

}
