public class Snippet__9489211 {

    public void deleteLocalFiles(String subdir) throws IOException {
        String[] localDirs = getLocalDirs();
        for (int i = 0; i < localDirs.length; i++) {
          FileSystem.getLocal(this).delete(new Path(localDirs[i], subdir), true);
        }
      }

}
