public class Snippet__9234588 {

    private static final class EESupportedGroupsProducer
                extends SupportedGroups implements HandshakeProducer {

            // Prevent instantiation of this class.
            private EESupportedGroupsProducer() {
                // blank
            }

            @Override
            public byte[] produce(ConnectionContext context,
                    HandshakeMessage message) throws IOException {
                // The producing happens in server side only.
                ServerHandshakeContext shc = (ServerHandshakeContext)context;

                // Is it a supported and enabled extension?
                if (!shc.sslConfig.isAvailable(EE_SUPPORTED_GROUPS)) {
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.fine(
                            "Ignore unavailable supported_groups extension");
                    }
                    return null;
                }

                // Produce the extension.
                //
                // Contains all groups the server supports, regardless of whether
                // they are currently supported by the client.
                ArrayList<NamedGroup> namedGroups = new ArrayList<>(
                        SupportedGroups.supportedNamedGroups.length);
                for (NamedGroup ng : SupportedGroups.supportedNamedGroups) {
                    if ((!SupportedGroups.enableFFDHE) &&
                        (ng.type == NamedGroupType.NAMED_GROUP_FFDHE)) {
                        continue;
                    }

                    if (ng.isAvailable(shc.activeProtocols) &&
                            ng.isSupported(shc.activeCipherSuites) &&
                            shc.algorithmConstraints.permits(
                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),
                                ng.algorithm, namedGroupParams.get(ng))) {
                        namedGroups.add(ng);
                    } else if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.fine(
                            "Ignore inactive or disabled named group: " + ng.name);
                    }
                }

                if (namedGroups.isEmpty()) {
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.warning("no available named group");
                    }

                    return null;
                }

                int vectorLen = namedGroups.size() << 1;
                byte[] extData = new byte[vectorLen + 2];
                ByteBuffer m = ByteBuffer.wrap(extData);
                Record.putInt16(m, vectorLen);
                for (NamedGroup namedGroup : namedGroups) {
                        Record.putInt16(m, namedGroup.id);
                }

                // Update the context.
                shc.conContext.serverRequestedNamedGroups =
                        Collections.<NamedGroup>unmodifiableList(namedGroups);
                SupportedGroupsSpec spec = new SupportedGroupsSpec(namedGroups);
                shc.handshakeExtensions.put(EE_SUPPORTED_GROUPS, spec);

                return extData;
            }
        }

}
