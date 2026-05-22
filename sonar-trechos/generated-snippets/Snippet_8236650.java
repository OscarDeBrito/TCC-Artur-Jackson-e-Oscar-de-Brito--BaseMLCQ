public class EP_HCenter extends BaseElementProcessor {
    private static final String _value_attribute = "value";
    private BooleanResult       _value;

    /**
     * constructor
     */

    public EP_HCenter() {
        super(null);
        _value = null;
    }

    /**
     * @return value
     *
     * @exception IOException if the value is malformed or missing
     */
    public boolean getValue() throws IOException {
        if (_value == null) {
            _value = BooleanConverter.extractBoolean(this.getValue(_value_attribute));
        }
        return _value.booleanValue();
    }
    
    /**
     * Setup whether or not the worksheet content is centered (horizontally)
     * on the page when it is printed
     * @exception IOException
     */
    public void endProcessing() throws IOException{
        this.getSheet().setHCenter(this.getValue());
    }

}   // end public class EP_HCenter
