public class ModelChangedEventOverlay extends OrionEventOverlay {
  protected ModelChangedEventOverlay() {}

  /** @return the number of characters added to the model. */
  public final native int addedCharCount() /*-{
        return this.addedCharCount;
    }-*/;

  /** @return The number of lines added to the model. */
  public final native int addedLineCount() /*-{
        return this.addedLineCount;
    }-*/;

  /** @return The number of characters removed from the model. */
  public final native int removedCharCount() /*-{
        return this.removedCharCount;
    }-*/;

  /** @return The number of lines removed from the model. */
  public final native int removedLineCount() /*-{
        return this.removedLineCount;
    }-*/;

  /** @return The character offset in the model where the change has occurred. */
  public final native int start() /*-{
        return this.start;
    }-*/;

  public final native String getText() /*-{
        return this.text;
  }-*/;
}
