public class Snippet__5176784 {

    @Override
        public Object apply(List<Object> args, Context context) throws ParseException {
          if(!initialized) {
            return null;
          }
          if(args.size() > 2) {
            throw new IllegalArgumentException("GEO_GET received more arguments than expected: " + args.size());
          }

          if(args.size() == 1 && args.get(0) instanceof String) {
            // If no fields are provided, return everything
            String ip = (String) args.get(0);
            if(ip == null || ip.trim().isEmpty()) {
              return null;
            }

            Optional<Map<String, String>> result = GeoLiteCityDatabase.INSTANCE.get(ip);
            return result.orElse(Collections.emptyMap());
          } else if (args.size() == 2 && args.get(1) instanceof List) {
            // If fields are provided, return just those fields.
            String ip = (String) args.get(0);
            @SuppressWarnings("unchecked")
            List<String> fields = (List) args.get(1);
            Optional<Map<String, String>> result = GeoLiteCityDatabase.INSTANCE.get(ip);

            // If only one field is requested, just return it directly
            if(fields.size() == 1 && result.isPresent()) {
              return result.get().get(fields.get(0));
            } else if (result.isPresent()) {
              // If multiple fields are requested, return all of them
              Map<String, String> filteredInfo = new HashMap<>();
              for(String field : fields) {
                Map<String, String> geoInfo = result.get();
                filteredInfo.put(field, geoInfo.get(field));
              }
              return filteredInfo;
            }
          }

          return null;
        }

}
