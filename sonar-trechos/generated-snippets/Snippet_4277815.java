public class UnsuitableDocumentException extends RatDocumentAnalysisException {

    private static final long serialVersionUID = 4202800209654402733L;

    public UnsuitableDocumentException() {
        super("This document is unsuitable for analysis");
    }

    public UnsuitableDocumentException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnsuitableDocumentException(String msg) {
        super(msg);
    }

    public UnsuitableDocumentException(Throwable cause) {
        super(cause);
    }

    
}
