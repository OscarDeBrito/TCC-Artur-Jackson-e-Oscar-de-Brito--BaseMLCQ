public class Snippet__4098093 {

    @Override
        public void close() throws HyracksDataException {
            Throwable failure = releaseResources();
            failure = CleanupUtils.close(writer, failure);
            if (failure != null) {
                throw HyracksDataException.create(failure);
            }
        }

}
