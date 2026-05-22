public class Snippet__3800392 {

    @Override
      public void init(ExtractorOutput output) {
        extractorOutput = output;
        trackOutput = output.track(0, C.TRACK_TYPE_AUDIO);
        wavHeader = null;
        output.endTracks();
      }

}
