public class Snippet__4693914 {

    public JobContext getJobContext(String resourceName) {
        if (_contextMap.containsKey(resourceName) && _contextMap.get(resourceName) != null) {
          return new JobContext(_contextMap.get(resourceName));
        }
        return null;
      }

}
