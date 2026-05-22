public class Snippet__7808358 {

    public static long compute8(long x) {
        x ^= x << 13;
        x ^= x >>> 7;
        x ^= (x << 17);
        return x;
      }

}
