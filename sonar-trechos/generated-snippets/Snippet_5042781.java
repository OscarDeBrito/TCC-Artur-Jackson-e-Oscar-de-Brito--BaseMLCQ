public class TurtleReaderFactory extends AbstractTriplesOnlyReaderFactory {
    
    public TurtleReaderFactory() {
        super(Lang.TURTLE, Lang.TTL, Lang.N3);
    }

    @Override
    public RecordReader<LongWritable, TripleWritable> createTripleReader() {
        return new TurtleReader();
    }

}
