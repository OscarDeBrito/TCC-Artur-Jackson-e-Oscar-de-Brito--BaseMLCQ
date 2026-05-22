public class Snippet__5828765 {

    public Map<String, Object> getPropertyMapping(String property, String itemType) {
            Map<String, Map<String, Object>> mappings = knownMappings.get(itemType);
            Map<String, Object> result = getPropertyMapping(property, mappings);
            if (result == null) {
                mappings = getPropertiesMapping(itemType);
                knownMappings.put(itemType, mappings);
                result = getPropertyMapping(property, mappings);
            }
            return result;
        }

}
