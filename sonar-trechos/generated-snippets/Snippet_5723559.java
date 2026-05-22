public class Snippet__5723559 {

    protected static void copyBlobStoreKeys(BlobStore bsFrom, Subject whoFrom, BlobStore bsTo, Subject whoTo) throws AuthorizationException, KeyAlreadyExistsException, IOException, KeyNotFoundException {
            Iterable<String> lfsKeys = () -> bsFrom.listKeys();
            for(String key : lfsKeys) {
                ReadableBlobMeta readable_meta = bsFrom.getBlobMeta(key, whoFrom);
                SettableBlobMeta meta = readable_meta.get_settable();
                InputStream in = bsFrom.getBlob(key, whoFrom);
                System.out.println("COPYING BLOB " + key + " FROM " + bsFrom + " TO " + bsTo);
                bsTo.createBlob(key, in, meta, whoTo);
                System.out.println("DONE CREATING BLOB " + key);
            }
        }

}
