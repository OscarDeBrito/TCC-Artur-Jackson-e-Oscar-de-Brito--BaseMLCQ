public class Snippet__7399310 {

    @Fallback
        TruffleObject doOther(@SuppressWarnings("unused") Object pointer) {
            if (allowNonForeign) {
                return null;
            } else {
                throw new LLVMPolyglotException(this, "Pointer does not point to a polyglot value.");
            }
        }

}
