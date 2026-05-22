@Internal
public interface CertPinManager {
    /**
     * Given a {@code hostname} and a {@code chain} this verifies that the
     * certificate chain includes pinned certificates if pinning is requested
     * for {@code hostname}.
     */
    void checkChainPinning(String hostname, List<X509Certificate> chain)
            throws CertificateException;
}
