public abstract class FakeMediaClockRenderer extends FakeRenderer implements MediaClock {

  public FakeMediaClockRenderer(Format... expectedFormats) {
    super(expectedFormats);
  }

  @Override
  public MediaClock getMediaClock() {
    return this;
  }
}
