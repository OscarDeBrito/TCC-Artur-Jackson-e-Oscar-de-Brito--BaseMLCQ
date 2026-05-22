public class InMemoryDataInputWrapper implements DataInputWrapper {
    /** Input to read data from */
    private final BigDataInput input;
    /** DataIndex which this wrapper belongs to */
    private final DataIndex index;

    /**
     * Constructor
     *
     * @param input Input to read data from
     * @param index DataIndex which this wrapper belongs to
     */
    public InMemoryDataInputWrapper(
        BigDataInput input, DataIndex index) {
      this.input = input;
      this.index = index;
    }

    @Override
    public DataInput getDataInput() {
      return input;
    }

    @Override
    public long finalizeInput(boolean deleteOnClose) {
      if (deleteOnClose) {
        data.remove(index).returnData();
      }
      return input.getPos();
    }
  }
