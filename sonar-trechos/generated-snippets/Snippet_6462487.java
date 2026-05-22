public class Snippet__6462487 {

    public static SignatureProcessor payloadSize ()
        {
            return new SignatureProcessor () {

                private long archiveSize;

                @Override
                public void init ( final long archiveSize )
                {
                    this.archiveSize = archiveSize;
                }

                @Override
                public void feedHeader ( final ByteBuffer header )
                {
                }

                @Override
                public void feedPayloadData ( final ByteBuffer data )
                {
                }

                @Override
                public void finish ( final Header<RpmSignatureTag> signature )
                {
                    signature.putSize ( this.archiveSize, RpmSignatureTag.PAYLOAD_SIZE, RpmSignatureTag.LONGARCHIVESIZE );
                }
            };
        }

}
