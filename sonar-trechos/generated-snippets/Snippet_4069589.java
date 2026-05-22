public class Snippet__4069589 {

    static final long hash(long key) {
            long hash = key * HashMixer;
            hash ^= hash >>> R;
            hash *= HashMixer;
            return hash;
        }

}
