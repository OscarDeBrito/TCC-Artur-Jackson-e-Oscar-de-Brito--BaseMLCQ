abstract class AbstractFuzzyHashProcessor extends AbstractProcessor {
    final protected static String ssdeep = "ssdeep";
    final protected static String tlsh = "tlsh";

    public static final AllowableValue allowableValueSSDEEP = new AllowableValue(
            ssdeep,
            ssdeep,
            "Uses ssdeep / SpamSum 'context triggered piecewise hash'.");
    public static final AllowableValue allowableValueTLSH = new AllowableValue(
            tlsh,
            tlsh,
            "Uses TLSH (Trend 'Locality Sensitive Hash'). Note: FlowFile Content must be at least 512 characters long");

    public static final PropertyDescriptor ATTRIBUTE_NAME = new PropertyDescriptor.Builder()
            .name("ATTRIBUTE_NAME")
            .displayName("Hash Attribute Name")
            .description("The name of the FlowFile Attribute that should hold the Fuzzy Hash Value")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .defaultValue("fuzzyhash.value")
            .build();

    public static final PropertyDescriptor HASH_ALGORITHM = new PropertyDescriptor.Builder()
            .name("HASH_ALGORITHM")
            .displayName("Hashing Algorithm")
            .description("The hashing algorithm utilised")
            .allowableValues(allowableValueSSDEEP, allowableValueTLSH)
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();


    protected List<PropertyDescriptor> descriptors;

    protected Set<Relationship> relationships;

    protected boolean checkMinimumAlgorithmRequirements(String algorithm, FlowFile flowFile) {
        // Check if content matches minimum length requirement
        if (algorithm.equals(tlsh) && flowFile.getSize() < 512 ) {
            return false;
        } else {
            return true;
        }
    }


    protected String generateHash(String algorithm, String content) {
        switch (algorithm) {
            case tlsh:
                return new TLSH(content).hash();
            case ssdeep:
                return new SpamSum().HashString(content);
            default:
                return null;
        }
    }
}
