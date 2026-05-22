public class Snippet__4038193 {

    public static <T> void structuralValueDecodeEncodeEqualInContext(
          Coder<T> coder, Coder.Context context, T value) throws Exception {
        assertEquals(
            coder.structuralValue(value), coder.structuralValue(decodeEncode(coder, context, value)));
      }

}
