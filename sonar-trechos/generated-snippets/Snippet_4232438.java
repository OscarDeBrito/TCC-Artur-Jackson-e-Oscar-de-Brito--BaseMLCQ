public class Snippet__4232438 {

    @Override
        public void nextBytes(byte[] bytes) {
        
            synchronized (OpenSslJnaCryptoRandom.class) {
                //this method is synchronized for now
                //to support multithreading https://wiki.openssl.org/index.php/Manual:Threads(3) needs to be done
            
                if(rdrandEnabled && OpenSslNativeJna.RAND_get_rand_method().equals(OpenSslNativeJna.RAND_SSLeay())) {
                    close();
                    throw new RuntimeException("rdrand should be used but default is detected");
                }

                ByteBuffer buf = ByteBuffer.allocateDirect(bytes.length);
                int retVal = OpenSslNativeJna.RAND_bytes(buf, bytes.length);
                throwOnError(retVal);
                buf.rewind();
                buf.get(bytes,0, bytes.length);
            }
        }

}
