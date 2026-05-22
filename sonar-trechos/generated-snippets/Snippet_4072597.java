public class Snippet__4072597 {

    public long computeIfAbsent(long key, LongLongFunction provider) {
            checkBiggerEqualZero(key);
            checkNotNull(provider);
            long h = hash(key);
            return getSection(h).put(key, ValueNotFound, (int) h, true, provider);
        }

}
