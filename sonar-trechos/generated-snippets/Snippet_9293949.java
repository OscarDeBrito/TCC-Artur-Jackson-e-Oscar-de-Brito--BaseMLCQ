public class Snippet__9293949 {

    private static final class CRSignatureSchemesUpdate
                implements HandshakeConsumer {
            // Prevent instantiation of this class.
            private CRSignatureSchemesUpdate() {
                // blank
            }

            @Override
            public void consume(ConnectionContext context,
                    HandshakeMessage message) throws IOException {
                // The consuming happens in client side only.
                ClientHandshakeContext chc = (ClientHandshakeContext)context;

                SignatureSchemesSpec spec =
                        (SignatureSchemesSpec)chc.handshakeExtensions.get(
                                SSLExtension.CR_SIGNATURE_ALGORITHMS);
                if (spec == null) {
                    // Ignore, no "signature_algorithms" extension requested.
                    return;
                }

                // update the context
                List<SignatureScheme> sss =
                        SignatureScheme.getSupportedAlgorithms(
                                chc.algorithmConstraints, chc.negotiatedProtocol,
                                spec.signatureSchemes);
                chc.peerRequestedSignatureSchemes = sss;

                // If no "signature_algorithms_cert" extension is present, then
                // the "signature_algorithms" extension also applies to
                // signatures appearing in certificates.
                SignatureSchemesSpec certSpec =
                        (SignatureSchemesSpec)chc.handshakeExtensions.get(
                                SSLExtension.CR_SIGNATURE_ALGORITHMS_CERT);
                if (certSpec == null) {
                    chc.peerRequestedCertSignSchemes = sss;
                    chc.handshakeSession.setPeerSupportedSignatureAlgorithms(sss);
                }
            }
        }

}
