public static class LongJsonUnmarshaller implements Unmarshaller<Long, JsonUnmarshallerContext> {
        public Long unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String longString = unmarshallerContext.readText();
            return (longString == null) ? null : Long.parseLong(longString);
        }

        private static final LongJsonUnmarshaller instance = new LongJsonUnmarshaller();

        public static LongJsonUnmarshaller getInstance() {
            return instance;
        }
    }
