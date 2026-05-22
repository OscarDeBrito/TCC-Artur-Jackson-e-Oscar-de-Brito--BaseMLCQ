public class RhinoInterpreterFactory implements InterpreterFactory {

    /**
     * The MIME types that Rhino can handle.
     */
    public static final String[] RHINO_MIMETYPES = {
        "application/ecmascript",
        "application/javascript",
        "text/ecmascript",
        "text/javascript",
    };

    /**
     * Builds a <code>RhinoInterpreterFactory</code>.
     */
    public RhinoInterpreterFactory() {
    }

    /**
     * Returns the mime-types to register this interpereter with.
     */
    public String[] getMimeTypes() {
        return RHINO_MIMETYPES;
    }

    /**
     * Creates an instance of <code>RhinoInterpreter</code> class.
     *
     * @param documentURL the url for the document which will be scripted
     * @param svg12 whether the document is an SVG 1.2 document
     */
    public Interpreter createInterpreter(URL documentURL, boolean svg12) {
        return createInterpreter(documentURL, svg12, null);
    }

    /**
     * Creates an instance of <code>RhinoInterpreter</code> class.
     *
     * @param documentURL the url for the document which will be scripted
     * @param svg12 whether the document is an SVG 1.2 document
     * @param imports The set of classes/packages to import (if
     *                the interpreter supports that), may be null.
     */
    public Interpreter createInterpreter(URL documentURL, boolean svg12,
                                         ImportInfo imports) {
        if (svg12) {
            return new SVG12RhinoInterpreter(documentURL, imports);
        }
        return new RhinoInterpreter(documentURL, imports);
    }
}
