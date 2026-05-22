public class Snippet__9388855 {

    static final class T10CertificateRequestMessage extends HandshakeMessage {
            final byte[] types;                 // certificate types
            final List<byte[]> authorities;     // certificate authorities

            T10CertificateRequestMessage(HandshakeContext handshakeContext,
                    X509Certificate[] trustedCerts, KeyExchange keyExchange) {
                super(handshakeContext);

                this.authorities = new ArrayList<>(trustedCerts.length);
                for (X509Certificate cert : trustedCerts) {
                    X500Principal x500Principal = cert.getSubjectX500Principal();
                    authorities.add(x500Principal.getEncoded());
                }

                this.types = ClientCertificateType.CERT_TYPES;
            }

            T10CertificateRequestMessage(HandshakeContext handshakeContext,
                    ByteBuffer m) throws IOException {
                super(handshakeContext);

                // struct {
                //     ClientCertificateType certificate_types<1..2^8-1>;
                //     DistinguishedName certificate_authorities<0..2^16-1>;
                // } CertificateRequest;
                if (m.remaining() < 4) {
                    throw handshakeContext.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                        "Incorrect CertificateRequest message: no sufficient data");
                }
                this.types = Record.getBytes8(m);

                int listLen = Record.getInt16(m);
                if (listLen > m.remaining()) {
                    throw handshakeContext.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                        "Incorrect CertificateRequest message:no sufficient data");
                }

                if (listLen > 0) {
                    this.authorities = new LinkedList<>();
                    while (listLen > 0) {
                        // opaque DistinguishedName<1..2^16-1>;
                        byte[] encoded = Record.getBytes16(m);
                        listLen -= (2 + encoded.length);
                        authorities.add(encoded);
                    }
                } else {
                    this.authorities = Collections.emptyList();
                }
            }

            String[] getKeyTypes() {
                return  ClientCertificateType.getKeyTypes(types);
            }

            X500Principal[] getAuthorities() {
                List<X500Principal> principals =
                        new ArrayList<>(authorities.size());
                for (byte[] encoded : authorities) {
                    X500Principal principal = new X500Principal(encoded);
                    principals.add(principal);
                }

                return principals.toArray(new X500Principal[0]);
            }

            @Override
            public SSLHandshake handshakeType() {
                return SSLHandshake.CERTIFICATE_REQUEST;
            }

            @Override
            public int messageLength() {
                int len = 1 + types.length + 2;
                for (byte[] encoded : authorities) {
                    len += encoded.length + 2;
                }
                return len;
            }

            @Override
            public void send(HandshakeOutStream hos) throws IOException {
                hos.putBytes8(types);

                int listLen = 0;
                for (byte[] encoded : authorities) {
                    listLen += encoded.length + 2;
                }

                hos.putInt16(listLen);
                for (byte[] encoded : authorities) {
                    hos.putBytes16(encoded);
                }
            }

            @Override
            public String toString() {
                MessageFormat messageFormat = new MessageFormat(
                        "\"CertificateRequest\": '{'\n" +
                        "  \"certificate types\": {0}\n" +
                        "  \"certificate authorities\": {1}\n" +
                        "'}'",
                        Locale.ENGLISH);

                List<String> typeNames = new ArrayList<>(types.length);
                for (byte type : types) {
                    typeNames.add(ClientCertificateType.nameOf(type));
                }

                List<String> authorityNames = new ArrayList<>(authorities.size());
                for (byte[] encoded : authorities) {
                    X500Principal principal = new X500Principal(encoded);
                    authorityNames.add(principal.toString());
                }
                Object[] messageFields = {
                    typeNames,
                    authorityNames
                };

                return messageFormat.format(messageFields);
            }
        }

}
