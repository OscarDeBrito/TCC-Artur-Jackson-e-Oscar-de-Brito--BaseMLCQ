public class Snippet__5516822 {

    @Override
      public byte[] encode(final T obj) {
        final Encoder<T> encoder = (Encoder<T>) clazzToEncoderMap.get(obj.getClass());
        if (encoder == null) {
          throw new RemoteRuntimeException("Encoder for " + obj.getClass() + " not known.");
        }

        final WakeTuplePBuf.Builder tupleBuilder = WakeTuplePBuf.newBuilder();
        tupleBuilder.setClassName(obj.getClass().getName());
        tupleBuilder.setData(ByteString.copyFrom(encoder.encode(obj)));
        return tupleBuilder.build().toByteArray();
      }

}
