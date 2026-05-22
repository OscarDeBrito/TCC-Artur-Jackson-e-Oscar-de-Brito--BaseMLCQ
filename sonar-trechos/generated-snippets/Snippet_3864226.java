public class Snippet__3864226 {

    @Override
        public boolean evaluate(Resource resource) {

          Set<Map<String, Object>> extendedPropertySet = joinedResources.get(resource);

          if (extendedPropertySet == null) {
            return predicate.evaluate(resource);
          }

          for (Map<String, Object> extendedProperties : extendedPropertySet) {
            Resource extendedResource = new ExtendedResourceImpl(resource, extendedProperties);

            if (predicate.evaluate(extendedResource)) {
              return true;
            }
          }
          return false;
        }

}
