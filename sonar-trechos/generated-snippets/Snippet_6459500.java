public class Snippet__6459500 {

    public Snippet__6459500(int inputSizeMax, ArrayCache arrayCache) {
            // We will use the *end* of the array so if the cache gives us
            // a bigger-than-requested array, we still want to use buf.length.
            buf = arrayCache.getByteArray(inputSizeMax - INIT_SIZE, false);
            pos = buf.length;
        }

}
