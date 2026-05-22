static class MimeMessageDecoder implements Store.Impl.Decoder<MimeMessage> {
        @Override
        public MimeMessage decode(Stream<Pair<BlobType, byte[]>> streams) {
            Preconditions.checkNotNull(streams);
            Map<BlobType,byte[]> pairs = streams.collect(ImmutableMap.toImmutableMap(Pair::getLeft, Pair::getRight));
            Preconditions.checkArgument(pairs.containsKey(HEADER_BLOB_TYPE));
            Preconditions.checkArgument(pairs.containsKey(BODY_BLOB_TYPE));

            return toMimeMessage(
                new SequenceInputStream(
                    new ByteArrayInputStream(pairs.get(HEADER_BLOB_TYPE)),
                    new ByteArrayInputStream(pairs.get(BODY_BLOB_TYPE))));
        }

        private MimeMessage toMimeMessage(InputStream inputStream) {
            try {
                return new MimeMessage(Session.getInstance(new Properties()), inputStream);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
