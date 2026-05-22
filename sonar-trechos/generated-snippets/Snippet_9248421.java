public class Snippet__9248421 {

    protected SignatureVisitor createSignatureRemapper(final SignatureVisitor signatureVisitor) {
            return new SignatureRemapper(signatureVisitor, this);
        }

}
