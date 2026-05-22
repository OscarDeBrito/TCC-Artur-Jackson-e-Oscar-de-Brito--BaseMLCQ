public class MediaParser extends AbstractParser<MediaSource> {

  public MediaParser() {
    super(AltFormat.MEDIA, MediaSource.class);
  }

  public <R extends MediaSource> R parse(ParseSource parseSource,
      InputProperties inProps, Class<R> resultClass) {
    
    // Ensure that the expected result type is MediaSource
    Preconditions.checkArgument(resultClass.isAssignableFrom(MediaSource.class),
        "Result class must be " + MediaSource.class.getName());
    
    InputStream inputStream = parseSource.getInputStream();
    Preconditions.checkNotNull(inputStream,
        "Parse source must be stream-based");   
    
    MediaStreamSource mediaSource = 
        new MediaStreamSource(inputStream, inProps.getContentType().toString());
   
    return resultClass.cast(mediaSource);
  }
}
