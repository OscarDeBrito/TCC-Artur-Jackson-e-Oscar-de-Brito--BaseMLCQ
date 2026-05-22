public class Snippet__7710744 {

    @Override
        public ByteBuf encodeHeader(ByteBufAllocator allocator) {
            ByteBuf buffer = allocator.buffer(31);

            // ttl:4
            buffer.writeInt((int) getTTL());

            // tracing:25
            CodecUtils.encodeTrace(getTracing(), buffer);

            // why~2
            CodecUtils.encodeString(getWhy(), buffer);

            return buffer;
        }

}
