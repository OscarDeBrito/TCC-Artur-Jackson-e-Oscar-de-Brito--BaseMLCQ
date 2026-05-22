public static class MapResolver implements Resolver
    {

        private final Map<String,String> map;

        public MapResolver(Map<String,String> map)
        {
            this.map = map;
        }

        @Override
        public String resolve(String variable, final Resolver resolver)
        {
            return map.get(variable);
        }
    }
