public class Snippet__4083880 {

    @Override
        public IBinaryHashFunction createBinaryHashFunction() {

            return new IBinaryHashFunction() {

                @Override
                public int hash(byte[] bytes, int offset, int length) {
                    return IntegerPointable.getInteger(bytes, offset + 4);
                }
            };
        }

}
