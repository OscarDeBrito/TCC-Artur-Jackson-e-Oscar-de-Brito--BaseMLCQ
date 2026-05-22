public class UrlEncode extends AbstractFunction {

    private static final String CHARSET_ENCODING = StandardCharsets.UTF_8.name();
    
    private static final List<String> desc = new LinkedList<>();

    private static final String KEY = "__urlencode"; //$NON-NLS-1$

    static {
        desc.add(JMeterUtils.getResString("urlencode_string")); //$NON-NLS-1$
    }

    private Object[] values;

    public UrlEncode() {
    }

    /** {@inheritDoc} */
    @Override
    public String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {
        String decodeString = ""; //$NON-NLS-1$
        try {
            String encodedString = ((CompoundVariable) values[0]).execute();
            decodeString = URLEncoder.encode(encodedString, CHARSET_ENCODING);
        } catch (UnsupportedEncodingException uee) {
            return null;
        }
        return decodeString;
    }

    /** {@inheritDoc} */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkParameterCount(parameters, 1);
        values = parameters.toArray();
    }

    /** {@inheritDoc} */
    @Override
    public String getReferenceKey() {
        return KEY;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }
}
