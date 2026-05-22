public class Snippet__3791275 {

    @Override
      protected void onStopped() {
        updateCurrentPosition();
        audioSink.pause();
        super.onStopped();
      }

}
