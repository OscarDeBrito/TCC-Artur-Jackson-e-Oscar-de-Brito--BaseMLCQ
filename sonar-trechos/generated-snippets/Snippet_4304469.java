public class CamMacVerifierChoice extends Asn1Choice {

    protected enum VerifierChoice implements EnumType {
        CAMMAC_verifierMac;

        @Override
        public int getValue() {
            return ordinal();
        }

        @Override
        public String getName() {
            return name();
        }
    }

    /** The CamMac's fields */
    private static Asn1FieldInfo[] fieldInfos = new Asn1FieldInfo[] {
            new ExplicitField(VerifierChoice.CAMMAC_verifierMac, CamMacVerifierMac.class)};

    public CamMacVerifierChoice() {
        super(fieldInfos);
    }

    public void setChoice(EnumType type, Asn1Type choice) {
        setChoiceValue(type, choice);
    }
}
