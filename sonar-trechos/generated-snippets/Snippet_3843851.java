public class Snippet__3843851 {

    ResourceInstance createStackVersionResource(String stackName,
                                                  String stackVersion) {
        Map<Resource.Type, String> mapIds = new HashMap<>();
        mapIds.put(Resource.Type.Stack, stackName);
        mapIds.put(Resource.Type.StackVersion, stackVersion);

        return createResource(Resource.Type.StackVersion, mapIds);
      }

}
