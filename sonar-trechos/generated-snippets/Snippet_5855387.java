public class Snippet__5855387 {

    void setPerUserTestWhileIdle(final Map<String, Boolean> userDefaultTestWhileIdle) {
            assertInitializationAllowed();
            if (perUserTestWhileIdle == null) {
                perUserTestWhileIdle = createMap();
            } else {
                perUserTestWhileIdle.clear();
            }
            perUserTestWhileIdle.putAll(userDefaultTestWhileIdle);
        }

}
