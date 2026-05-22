@Converter(loader = true, ignoreOnLoadError = true)
public final class StringSequenceNumberConverter {

    private StringSequenceNumberConverter() {
    }

    @Converter
    public static SequenceNumberProvider toSequenceNumberProvider(String sequenceNumber) {
        return new StaticSequenceNumberProvider(sequenceNumber);
    }
}
