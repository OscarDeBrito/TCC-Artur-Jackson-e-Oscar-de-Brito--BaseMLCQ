public class Snippet__6445236 {

    @Override
            public ContentFilter decode(UaDecoder decoder) throws UaSerializationException {
                ContentFilterElement[] elements =
                    decoder.readBuiltinStructArray(
                        "Elements",
                        ContentFilterElement.class
                    );

                return new ContentFilter(elements);
            }

}
