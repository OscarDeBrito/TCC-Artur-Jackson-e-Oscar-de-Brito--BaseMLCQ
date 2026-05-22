public class Snippet__7023392 {

    @Provides
      @Singleton
      @Named("read")
      public Supplier<Request> provideRead(
          @Named("request.id") final Function<Map<String, String>, String> id, final Scheme scheme,
          @ReadHost final Function<Map<String, String>, String> host,
          @Nullable @Named("port") final Integer port,
          @Nullable @Named("uri.root") final String uriRoot,
          @Named("read.container") final Function<Map<String, String>, String> container,
          @Nullable @Named("api.version") final String apiVersion,
          @Nullable @ReadObjectName final Function<Map<String, String>, String> object,
          @ReadHeaders final Map<String, Function<Map<String, String>, String>> headers,
          @Named("read.context") final List<Function<Map<String, String>, String>> context,
          @Nullable @Named("credentials") final Function<Map<String, String>, Credential> credentials,
          @Named("virtualhost") final boolean virtualHost,
          @Named("read.sseCSource") final boolean encryptedSourceObject) {

        final Map<String, Function<Map<String, String>, String>> queryParameters =
            Collections.emptyMap();

        if (encryptedSourceObject) {
          if (!headers.containsKey("x-amz-server-side-encryption-customer-algorithm")) {
            headers.put("x-amz-server-side-encryption-customer-algorithm",
                provideSSEEncryptionAlgorithm());
          }
          if (!headers.containsKey("x-amz-server-side-encryption-customer-key")) {
            headers.put("x-amz-server-side-encryption-customer-key", provideSSEEncryptionKey());
          }
          if (!headers.containsKey("x-amz-server-side-encryption-customer-key-MD5")) {
            headers.put("x-amz-server-side-encryption-customer-key-MD5", provideSSEKeyMD5());
          }
        }

        final Supplier<Body> bodySupplier = Suppliers.of(Bodies.none());
        final Function<Map<String, String>, Body> body = MoreFunctions.forSupplier(bodySupplier);

        return createRequestSupplier(Operation.READ, id, Method.GET, scheme, host, port, uriRoot,
            container, apiVersion, object, queryParameters, headers, context, null, body, credentials,
            virtualHost, null, null, false, null);
      }

}
