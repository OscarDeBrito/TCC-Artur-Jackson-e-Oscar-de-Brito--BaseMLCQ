@Component
public class StubDescriptorJsonParser {

    @Autowired
    @Qualifier("conditionDescriptorJsonParser")
    private ObjectParser<ConditionDescriptor> conditionDescriptorJsonParser;
    @Autowired
    @Qualifier("responseDescriptorJsonParser")
    private ObjectParser<ResponseDescriptor> responseDescriptorJsonParser;
    @Autowired
    private DialogDescriptorAttributeJsonParser dialogDescriptorAttributeJsonParser;
    @Autowired
    private SequenceDescriptorJsonParser sequenceDescriptorJsonParser;
    @Autowired
    private InterceptorDescriptorJsonParser interceptorDescriptorJsonParser;

    /**
     * Builds a new {@link StubDescriptor} domain model from a given JSON Object.
     *
     * @param jsonStubDescriptor the JSON Object of the stub descriptor
     * @return the newly built StubDescriptor
     */
    public StubDescriptor parse(final JSONObject jsonStubDescriptor) {
        JSONObject root = jsonStubDescriptor.getJSONObject("wilmaStubConfiguration");
        StubDescriptorAttributes attributes = getStubDescriptorAttributes(root);
        List<DialogDescriptor> dialogDescriptors = getDialogDescriptors(root);
        List<InterceptorDescriptor> interceptorDescriptors = getInterceptorDescriptors(root);
        List<SequenceDescriptor> sequenceDescriptors = sequenceDescriptorJsonParser.parse(root, dialogDescriptors);
        //set validity - it is valid only if it has something inside
        if (dialogDescriptors.size() + interceptorDescriptors.size() + sequenceDescriptors.size() > 0) {
            attributes.setValid(true);
        }
        StubDescriptor stubDescriptor = new StubDescriptor(attributes, dialogDescriptors, interceptorDescriptors, sequenceDescriptors);
        return stubDescriptor;
    }

    private StubDescriptorAttributes getStubDescriptorAttributes(final JSONObject root) {
        String groupName = root.has("groupName") ? root.getString("groupName") : "Default";
        String activeText = root.has("active") ? root.getString("active") : "true";
        boolean active;
        active = Boolean.valueOf(activeText);
        return new StubDescriptorAttributes(groupName, active);
    }

    private List<DialogDescriptor> getDialogDescriptors(final JSONObject root) {
        List<DialogDescriptor> dialogDescriptors = new ArrayList<DialogDescriptor>();
        if (root.has("dialogDescriptors")) {
            JSONArray dialogDescriptorsArray = root.getJSONArray("dialogDescriptors");
            for (int i = 0; i < dialogDescriptorsArray.length(); i++) {
                JSONObject dialogDescriptor = dialogDescriptorsArray.getJSONObject(i);
                DialogDescriptorAttributes attributes = dialogDescriptorAttributeJsonParser.getAttributes(dialogDescriptor);
                ConditionDescriptor conditionDescriptor = conditionDescriptorJsonParser.parseObject(dialogDescriptor.getJSONObject(ConditionDescriptor.TAG_NAME_JSON), root);
                ResponseDescriptor responseDescriptor = responseDescriptorJsonParser.parseObject(dialogDescriptor.getJSONObject(ResponseDescriptor.TAG_NAME_JSON), root);
                dialogDescriptors.add(new DialogDescriptor(attributes, conditionDescriptor, responseDescriptor));
            }
        }
        return dialogDescriptors;
    }

    private List<InterceptorDescriptor> getInterceptorDescriptors(final JSONObject root) {
        List<InterceptorDescriptor> interceptorDescriptors = new ArrayList<>();
        if (root.has("interceptors")) {
            JSONArray interceptorArray = root.getJSONArray("interceptors");
            for (int i = 0; i < interceptorArray.length(); i++) {
                InterceptorDescriptor interceptorDescriptor = interceptorDescriptorJsonParser.parseObject(interceptorArray.getJSONObject(i), root);
                interceptorDescriptors.add(interceptorDescriptor);
            }
        }
        return interceptorDescriptors;
    }
}
