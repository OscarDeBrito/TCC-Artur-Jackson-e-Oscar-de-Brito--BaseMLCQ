public class Snippet__7180104 {

    private void storeFile(History histNew, File file, Repository repo,
                boolean mergeHistory) throws HistoryException {

            File cacheFile;
            try {
                cacheFile = getCachedFile(file);
            } catch (ForbiddenSymlinkException e) {
                LOGGER.log(Level.FINER, e.getMessage());
                return;
            }
            History history = histNew;

            File dir = cacheFile.getParentFile();
            if (!dir.isDirectory() && !dir.mkdirs()) {
                throw new HistoryException(
                        "Unable to create cache directory '" + dir + "'.");
            }

            if (mergeHistory && cacheFile.exists()) {
                history = mergeOldAndNewHistory(cacheFile, histNew, repo);
            }

            // If the merge failed, null history will be returned.
            // In such case store at least new history as a best effort.
            if (history != null) {
                writeHistoryToFile(dir, history, cacheFile);
            } else {
                writeHistoryToFile(dir, histNew, cacheFile);
            }
        }

}
