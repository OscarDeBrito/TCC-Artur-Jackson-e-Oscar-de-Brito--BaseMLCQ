public class Snippet__8579381 {

    public long getCallMethodCount(String callQueueName, String methodName) {
        long methodCount;

        Map<String, Long> methodCountMap = callQueueMethodCountsSummary.getOrDefault(callQueueName, null);

        if (null != methodCountMap) {
          methodCount = methodCountMap.getOrDefault(methodName, 0L);
        } else {
          methodCount = 0L;
        }

        return methodCount;
      }

}
