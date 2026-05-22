public class Snippet__7881040 {

    @Override
      public ArchiveMemberPath getRelativeArchiveMemberPath(SourcePath sourcePath) {
        Preconditions.checkState(sourcePath instanceof ArchiveMemberSourcePath);
        ArchiveMemberSourcePath archiveMemberSourcePath = (ArchiveMemberSourcePath) sourcePath;

        Path archiveRelativePath = getRelativePath(archiveMemberSourcePath.getArchiveSourcePath());

        return ArchiveMemberPath.of(archiveRelativePath, archiveMemberSourcePath.getMemberPath());
      }

}
