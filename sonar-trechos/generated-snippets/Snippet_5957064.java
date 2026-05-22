public class Snippet__5957064 {

    public void addHttpFilter(HttpListener listener, String regex) {
            synchronized (filterRegistry) {
                if (!filterRegistry.containsKey(regex)) {
                    filterRegistry.put(regex, new ArrayList<HttpListener>());
                }
                filterRegistry.get(regex).add(listener);
            }
        }

}
