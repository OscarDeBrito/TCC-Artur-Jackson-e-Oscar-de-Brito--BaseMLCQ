public class Snippet__6445781 {

    private static Map<String, String> load() {
            Map<String, String> attributes = Maps.newConcurrentMap();

            for (URI uri : uris()) {
                try {
                    attributes.putAll(load(uri.toURL()));
                } catch (Throwable t) {
                    logger.error("load(): '{}' failed", uri, t);
                }
            }

            return attributes;
        }

}
