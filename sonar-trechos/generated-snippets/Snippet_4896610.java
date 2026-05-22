public class Snippet__4896610 {

    @Override
        public boolean releaseCheckpoint(String checkpoint) {
            log.info("Released checkpoint [{}]", checkpoint);
            return store.release(checkpoint);
        }

}
