public class Snippet__4073701 {

    @Override
        public boolean parse(DataOutput out) throws HyracksDataException {
            try {
                resetPools();
                return parseAdmInstance(recordType, out);
            } catch (ParseException e) {
                e.setLocation(filename, admLexer.getLine(), admLexer.getColumn());
                throw e;
            } catch (IOException e) {
                throw new ParseException(e, filename, admLexer.getLine(), admLexer.getColumn());
            }
        }

}
