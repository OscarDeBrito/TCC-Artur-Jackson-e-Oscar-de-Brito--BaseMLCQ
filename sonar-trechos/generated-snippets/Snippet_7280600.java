public final class CartClickActionEvent extends ScreenActionEvent implements Parcelable {
  public static final String ACTION = CartClickActionEvent.class.getSimpleName();

  public CartClickActionEvent() {
    super(ACTION);
  }

  protected CartClickActionEvent(Parcel in) {
    super(in);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<CartClickActionEvent> CREATOR = new Creator<CartClickActionEvent>() {
    @Override
    public CartClickActionEvent createFromParcel(Parcel in) {
      return new CartClickActionEvent(in);
    }

    @Override
    public CartClickActionEvent[] newArray(int size) {
      return new CartClickActionEvent[size];
    }
  };
}
