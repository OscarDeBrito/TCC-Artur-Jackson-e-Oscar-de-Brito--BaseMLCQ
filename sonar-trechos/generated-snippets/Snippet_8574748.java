public class Snippet__8574748 {

    public static void concurrentVisitReferencedFiles(final Configuration conf, final FileSystem fs,
          final SnapshotManifest manifest, final String desc, final StoreFileVisitor visitor)
          throws IOException {

        final Path snapshotDir = manifest.getSnapshotDir();
        List<SnapshotRegionManifest> regionManifests = manifest.getRegionManifests();
        if (regionManifests == null || regionManifests.isEmpty()) {
          LOG.debug("No manifest files present: " + snapshotDir);
          return;
        }

        ExecutorService exec = SnapshotManifest.createExecutor(conf, desc);

        try {
          concurrentVisitReferencedFiles(conf, fs, manifest, exec, visitor);
        } finally {
          exec.shutdown();
        }
      }

}
