public class Snippet__4045122 {

    public static ITypeTraits[] serdesToTypeTraits(ISerializerDeserializer[] serdes, int payloadSize) {
            ITypeTraits[] typeTraits = new ITypeTraits[serdes.length + 1];
            for (int i = 0; i < serdes.length; i++) {
                typeTraits[i] = serdeToTypeTrait(serdes[i]);
            }
            typeTraits[serdes.length] = new FixedLengthTypeTrait(payloadSize);
            return typeTraits;
        }

}
