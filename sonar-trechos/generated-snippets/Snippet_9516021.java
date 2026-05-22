public class Snippet__9516021 {

    private void lostFoundInit(DFSClient dfs) {
        lfInited = true;
        try {
          String lfName = "/lost+found";

          final HdfsFileStatus lfStatus = dfs.getFileInfo(lfName);
          if (lfStatus == null) { // not exists
            lfInitedOk = dfs.mkdirs(lfName, null, true);
            lostFound = lfName;
          } else if (!lfStatus.isDirectory()) { // exists but not a directory
            LOG.warn("Cannot use /lost+found : a regular file with this name exists.");
            lfInitedOk = false;
          }  else { // exists and is a directory
            lostFound = lfName;
            lfInitedOk = true;
          }
        }  catch (Exception e) {
          e.printStackTrace();
          lfInitedOk = false;
        }
        if (lostFound == null) {
          LOG.warn("Cannot initialize /lost+found .");
          lfInitedOk = false;
          internalError = true;
        }
      }

}
