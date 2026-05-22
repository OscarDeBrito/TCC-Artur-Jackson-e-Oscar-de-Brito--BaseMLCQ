public class Snippet__4210395 {

    private static final class EncryptionData
        {
            final Cipher cipher;
            final ICompressor compressor;
            final ImmutableMap<String, Object> params;

            private EncryptionData(Cipher cipher, ICompressor compressor, ImmutableMap<String, Object> params)
            {
                this.cipher = cipher;
                this.compressor = compressor;
                this.params = params;
            }
        }

}
