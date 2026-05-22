public class Snippet__6029853 {

    public Snippet__6029853(X509Certificate[] certificateChain, InetSocketAddress peerAddress) {
    		super(peerAddress);
    		if (certificateChain == null) {
    			throw new NullPointerException("Certificate chain must not be null");
    		} else {
    			setCertificateChain(certificateChain);
    			calculateLength();
    		}
    	}

}
