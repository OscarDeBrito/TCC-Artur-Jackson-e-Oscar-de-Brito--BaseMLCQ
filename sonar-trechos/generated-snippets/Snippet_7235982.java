@SuppressWarnings({"UnusedDeclaration"})
@Implements(value = Icon.class, minSdk = M)
public class ShadowIcon {

  @RealObject
  private Icon realIcon;

  @HiddenApi
  @Implementation
  public int getType() {
    return directlyOn(realIcon, Icon.class).getType();
  }

  @HiddenApi
  @Implementation
  public int getResId() {
    return directlyOn(realIcon, Icon.class).getResId();
  }

  @HiddenApi
  @Implementation
  public Bitmap getBitmap() {
    return directlyOn(realIcon, Icon.class).getBitmap();
  }

  @HiddenApi
  @Implementation
  public Uri getUri() {
    return directlyOn(realIcon, Icon.class).getUri();
  }

  @HiddenApi
  @Implementation
  public int getDataLength() {
    return directlyOn(realIcon, Icon.class).getDataLength();
  }

  @HiddenApi
  @Implementation
  public int getDataOffset() {
    return directlyOn(realIcon, Icon.class).getDataOffset();
  }

  @HiddenApi
  @Implementation
  public byte[] getDataBytes() {
    return directlyOn(realIcon, Icon.class).getDataBytes();
  }
}
