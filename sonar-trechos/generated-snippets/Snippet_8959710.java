public class Snippet__8959710 {

    @Override
      public void initialize(Configuration conf, Properties tbl) throws SerDeException {
        super.initialize(conf, tbl);
        try {
          @SuppressWarnings("unchecked")
          Parser<? extends Message> tmpParser = (Parser<? extends Message>)protoMessageClass
              .getField("PARSER").get(null);
          this.parser = tmpParser;
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
            | SecurityException e) {
          throw new SerDeException("Unable get PARSER from class: " + protoMessageClass.getName(), e);
        }
      }

}
