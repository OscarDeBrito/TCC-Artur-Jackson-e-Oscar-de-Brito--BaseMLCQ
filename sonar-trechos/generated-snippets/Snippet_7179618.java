public class TroffAnalyzer extends TextAnalyzer {

    /**
     * Creates a new instance of TroffAnalyzer
     * @param factory defined instance for the analyzer
     */
    protected TroffAnalyzer(AnalyzerFactory factory) {
        super(factory, new JFlexTokenizer(new TroffFullTokenizer(
                AbstractAnalyzer.DUMMY_READER)));
    }    

    /**
     * Gets a version number to be used to tag processed documents so that
     * re-analysis can be re-done later if a stored version number is different
     * from the current implementation.
     * @return 20180112_00
     */
    @Override
    protected int getSpecializedVersionNo() {
        return 20180112_00; // Edit comment above too!
    }
    
    @Override
    public void analyze(Document doc, StreamSource src, Writer xrefOut) throws IOException {        
        //this is to explicitly use appropriate analyzers tokenstream to workaround #1376 symbols search works like full text search 
        this.symbolTokenizer.setReader(getReader(src.getStream()));
        OGKTextField full = new OGKTextField(QueryBuilder.FULL,
            symbolTokenizer);
        doc.add(full);

        if (xrefOut != null) {
            try (Reader in = getReader(src.getStream())) {
                WriteXrefArgs args = new WriteXrefArgs(in, xrefOut);
                args.setProject(project);
                Xrefer xref = writeXref(args);

                addNumLines(doc, xref.getLineNumber());
                addLOC(doc, xref.getLOC());
            }
        }
    }

    /**
     * Creates a wrapped {@link TroffXref} instance.
     * @param reader the data to produce xref for
     * @return an xref instance
     */
    @Override
    protected Xrefer newXref(Reader reader) {
        return new TroffXref(reader);
    }
}
