public class Snippet__6323354 {

    @Override
        public KapuaResponseMessage<?, ?> publish(KapuaDataMessage requestMessage, Long timeout)
                throws KapuaException {
            //
            // Argument validation
            ArgumentValidator.notNull(requestMessage.getScopeId(), "scopeId");
            ArgumentValidator.notNull(requestMessage.getDeviceId(), "deviceId");
            ArgumentValidator.notNull(requestMessage.getClientId(), "clientId");

            //
            // Check Access
            AUTHORIZATION_SERVICE.checkPermission(PERMISSION_FACTORY.newPermission(StreamDomains.STREAM_DOMAIN, Actions.write, requestMessage.getScopeId()));

            //
            // Do publish
            TransportFacade<?, ?, TransportMessage<?, ?>, ?> transportFacade = null;
            try {

                Device device = DEVICE_REGISTRY_SERVICE.find(requestMessage.getScopeId(), requestMessage.getDeviceId());

                if (device == null) {
                    throw new KapuaEntityNotFoundException(Device.TYPE, requestMessage.getDeviceId());
                }

                String nodeUri = device.getConnection().getServerIp();

                //
                // Borrow a KapuaClient
                transportFacade = borrowClient(nodeUri);

                //
                // Get Kura to transport translator for the request and vice versa
                Translator<KapuaDataMessage, KuraDataMessage> translatorKapuaKura = getTranslator(KapuaDataMessage.class, KuraDataMessage.class);
                Translator<KuraDataMessage, ?> translatorKuraTransport = getTranslator(KuraDataMessage.class, transportFacade.getMessageClass());

                KuraDataMessage kuraDataMessage = translatorKapuaKura.translate(requestMessage);

                //
                // Do send
                try {
                    // Set current timestamp
                    kuraDataMessage.setTimestamp(new Date());

                    // Send
                    transportFacade.sendAsync((TransportMessage<?, ?>) translatorKuraTransport.translate(kuraDataMessage));

                } catch (KapuaException e) {
                    throw new KuraMqttDeviceCallException(KuraMqttDeviceCallErrorCodes.CLIENT_SEND_ERROR,
                            e,
                            (Object[]) null);
                }
            } catch (KapuaException ke) {
                throw new KuraMqttDeviceCallException(KuraMqttDeviceCallErrorCodes.CALL_ERROR,
                        ke,
                        (Object[]) null);
            } finally {
                if (transportFacade != null) {
                    transportFacade.clean();
                }
            }

            return null;
        }

}
