static class BatchChangeSecretSerializer extends StdSerializer<BatchChangeSecretRequest> {

        private static final long serialVersionUID = 8880285813370852371L;

        BatchChangeSecretSerializer() {
            super(BatchChangeSecretRequest.class);
        }

        @Override
        public void serialize(BatchChangeSecretRequest request, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeObject(request.getChangeSecrets());
        }

    }
