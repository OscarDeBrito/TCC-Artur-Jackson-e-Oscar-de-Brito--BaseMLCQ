public class Snippet__6297548 {

    @Override
        public ByteBuffer getPayload()
        {
            if(!delegate.hasPayload()) {
                return null;
            }
            return delegate.getPayload().asReadOnlyBuffer();
        }

}
