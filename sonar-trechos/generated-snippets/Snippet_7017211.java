public final class GcpKmsAead implements Aead {

  /** This client knows how to talk to Google Cloud KMS. */
  private final CloudKMS kmsClient;

  // The location of a CryptoKey in Google Cloud KMS.
  // Valid values have this format: projects/*/locations/*/keyRings/*/cryptoKeys/*.
  // See https://cloud.google.com/kms/docs/object-hierarchy.
  private final String kmsKeyUri;

  public GcpKmsAead(CloudKMS kmsClient, String keyUri) throws GeneralSecurityException {
    this.kmsClient = kmsClient;
    this.kmsKeyUri = keyUri;
  }

  @Override
  public byte[] encrypt(final byte[] plaintext, final byte[] aad) throws GeneralSecurityException {
    try {
      EncryptRequest request =
          new EncryptRequest().encodePlaintext(plaintext).encodeAdditionalAuthenticatedData(aad);
      EncryptResponse response =
          this.kmsClient
              .projects()
              .locations()
              .keyRings()
              .cryptoKeys()
              .encrypt(this.kmsKeyUri, request)
              .execute();
      return response.decodeCiphertext();
    } catch (IOException e) {
      throw new GeneralSecurityException("encryption failed", e);
    }
  }

  @Override
  public byte[] decrypt(final byte[] ciphertext, final byte[] aad) throws GeneralSecurityException {
    try {
      DecryptRequest request =
          new DecryptRequest().encodeCiphertext(ciphertext).encodeAdditionalAuthenticatedData(aad);
      DecryptResponse response =
          this.kmsClient
              .projects()
              .locations()
              .keyRings()
              .cryptoKeys()
              .decrypt(this.kmsKeyUri, request)
              .execute();
      return response.decodePlaintext();
    } catch (IOException e) {
      throw new GeneralSecurityException("decryption failed", e);
    }
  }
}
