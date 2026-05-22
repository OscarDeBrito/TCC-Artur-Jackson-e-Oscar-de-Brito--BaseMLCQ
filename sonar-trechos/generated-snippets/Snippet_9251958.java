public class Snippet__9251958 {

    public StringList getErrorCodes() {
            if (fErrors == null || fErrors.length == 0) {
                return StringListImpl.EMPTY_LIST;
            }
            return new PSVIErrorList(fErrors, true);
        }

}
