public class JaxrsEndPointAnalyzer extends AbstractSingleTypeEndpointAnalyzer {
    private static final JaxrsEndPointAnalyzer INSTANCE = new JaxrsEndPointAnalyzer();

    private JaxrsEndPointAnalyzer() {
        super(JaxrsDefinitions.TYPE);
    }

    public static final JaxrsEndPointAnalyzer getInstance() {
        return INSTANCE;
    }

    @Override
    protected EndPointAnalysis makeEndPoint(Frame frame, int depth) {
        Operation op = frame.getOperation();
        EndPointName endPointName = EndPointName.valueOf(op);
        String example = EndPointAnalysis.getHttpExampleRequest(frame);
        return new EndPointAnalysis(endPointName, op.getLabel(), example, getOperationScore(op, depth), op);
    }
}
