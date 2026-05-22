public final class MultimediaContentHandlers implements ContentHandlerFactory {

    @Override
    public ContentHandler createContentHandler(String mimetype) {
        switch (mimetype) {
            case "audio/aiff":      return new aiff();
            case "audio/basic":     return new basic();
            case "audio/wav":       return new wav();
            case "audio/x-aiff":    return new x_aiff();
            case "audio/x-wav":     return new x_wav();
            case "image/gif":       return new gif();
            case "image/jpeg":      return new jpeg();
            case "image/png":       return new png();
            case "image/x-xbitmap": return new x_xbitmap();
            case "image/x-xpixmap": return new x_xpixmap();
            default:                return null;
        }
    }
}
