public class Snippet__5659423 {

    public static final class Since2014 extends InternationalStringAdapter {
            /** Empty constructor used only by JAXB. */
            public Since2014() {
            }

            /**
             * Wraps the given value in an ISO 19115-3 element, unless we are marshalling an older document.
             *
             * @return a non-null value only if marshalling ISO 19115-3 or newer.
             */
            @Override public GO_CharacterString marshal(final InternationalString value) {
                return FilterByVersion.CURRENT_METADATA.accept() ? super.marshal(value) : null;
            }
        }

}
