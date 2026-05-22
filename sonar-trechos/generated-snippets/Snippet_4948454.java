public class AtmosUtils {

   @Inject
   SignRequest signer;

   @Inject
   ParseSax.Factory factory;

   @Inject
   Provider<ErrorHandler> errorHandlerProvider;

   public AtmosError parseAtmosErrorFromContent(HttpCommand command, HttpResponse response, InputStream content)
            throws HttpException {
      AtmosError error = factory.create(errorHandlerProvider.get()).parse(content);
      if (error.getCode() == AtmosErrorCode.SIGNATURE_MISMATCH.getCode()) {
         error.setStringSigned(signer.createStringToSign(command.getCurrentRequest()));
      }
      return error;

   }

   public static String putBlob(final AtmosClient sync, Crypto crypto, BlobToObject blob2Object, String container,
            Blob blob, PutOptions options) {
      final String path = container + "/" + blob.getMetadata().getName();
      final AtmosObject object = blob2Object.apply(blob);

      URI uri;
      try {
         uri = sync.createFile(container, object, options);
      } catch (KeyAlreadyExistsException e) {
         deletePathAndEnsureGone(sync, path);
         uri = sync.createFile(container, object, options);
      }

      // return object ID as the ETag
      String objectId = uri.getPath();
      String prefix = "/rest/objects/";
      checkState(objectId.startsWith(prefix), objectId);
      return objectId.substring(prefix.length());
   }
   
   public static void deletePathAndEnsureGone(final AtmosClient sync, String path) {
      checkState(retry(new Predicate<String>() {
         public boolean apply(String in) {
            try {
               sync.deletePath(in);
               return !sync.pathExists(in);
            } catch (ContainerNotFoundException e) {
               return true;
            }
         }
      }, 3000).apply(path), "%s still exists after deleting!", path);
   }

   public AtmosError parseAtmosErrorFromContent(HttpCommand command, HttpResponse response, String content)
            throws HttpException {
      return parseAtmosErrorFromContent(command, response, new ByteArrayInputStream(content.getBytes()));
   }

   public static String adjustContainerIfDirOptionPresent(String container,
            org.jclouds.blobstore.options.ListContainerOptions options) {
      if (options != org.jclouds.blobstore.options.ListContainerOptions.NONE) {
         // if (options.isRecursive()) {
         // throw new UnsupportedOperationException("recursive not currently supported in emcsaas");
         // }
         if (options.getDir() != null) {
            container = container + "/" + options.getDir();
         }
      }
      return container;
   }
}
