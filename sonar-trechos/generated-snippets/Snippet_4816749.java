public class Snippet__4816749 {

    public static void putAll(Map<String, String> additionalEnvironment) {
        Map<String, String> environment = new HashMap<String, String>(System.getenv());
        environment.putAll(additionalEnvironment);
        updateEnvironment(environment);
      }

}
