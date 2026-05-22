public class EndsWith extends StringComparisonFilter {

    public EndsWith(RecordPathSegment recordPath, final RecordPathSegment searchValuePath) {
        super(recordPath, searchValuePath);
    }

    @Override
    protected boolean isMatch(final String fieldValue, final String comparison) {
        return fieldValue.endsWith(comparison);
    }

}
