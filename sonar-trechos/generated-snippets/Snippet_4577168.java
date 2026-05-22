public class PCLDocumentHandlerMaker extends AbstractIFDocumentHandlerMaker {

    private static final String[] MIMES = new String[] {
            MimeConstants.MIME_PCL,
            MimeConstants.MIME_PCL_ALT
    };

    /** {@inheritDoc} */
    public IFDocumentHandler makeIFDocumentHandler(IFContext ifContext) {
        return new PCLDocumentHandler(ifContext);
    }

    /** {@inheritDoc} */
    public boolean needsOutputStream() {
        return true;
    }

    /** {@inheritDoc} */
    public String[] getSupportedMimeTypes() {
        return MIMES;
    }

}
