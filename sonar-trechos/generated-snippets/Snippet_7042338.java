class FilterStaxUnmarshaller implements Unmarshaller<Filter, StaxUnmarshallerContext> {

    private static FilterStaxUnmarshaller instance = new FilterStaxUnmarshaller();

    public static FilterStaxUnmarshaller getInstance() {
        return instance;
    }

    private FilterStaxUnmarshaller() {
    }

    @Override
    public Filter unmarshall(StaxUnmarshallerContext context) throws Exception {
        int originalDepth = context.getCurrentDepth();
        int targetDepth = originalDepth + 1;

        if (context.isStartOfDocument()) {
            targetDepth += 1;
        }

        Filter filter = new Filter();

        while (true) {
            XMLEvent xmlEvent = context.nextEvent();
            if (xmlEvent.isEndDocument()) {
                return filter;
            }

            if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
                if (context.testExpression("S3Key", targetDepth)) {
                    filter.withS3KeyFilter(S3KeyFilterStaxUnmarshaller.getInstance().unmarshall(context));
                }
            } else if (xmlEvent.isEndElement()) {
                if (context.getCurrentDepth() < originalDepth) {
                    return filter;
                }
            }
        }
    }

}
