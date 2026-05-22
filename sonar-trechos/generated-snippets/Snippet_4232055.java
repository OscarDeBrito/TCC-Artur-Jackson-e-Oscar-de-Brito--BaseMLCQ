public class Snippet__4232055 {

    public static final class UnicodeExtraFieldPolicy {
            /**
             * Always create Unicode extra fields.
             */
            public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy("always");
            /**
             * Never create Unicode extra fields.
             */
            public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
            /**
             * Create Unicode extra fields for filenames that cannot be
             * encoded using the specified encoding.
             */
            public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE =
                new UnicodeExtraFieldPolicy("not encodeable");

            private final String name;
            private UnicodeExtraFieldPolicy(final String n) {
                name = n;
            }
            @Override
            public String toString() {
                return name;
            }
        }

}
