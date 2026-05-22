public class Snippet__7026284 {

    public void scanLibrary(boolean waitTillComplete)
      {
        doThoroughLibScan = true;
        establishMountPoints();
        if (!waitTillComplete)
          libraryImportScan();
        else
        {
          synchronized (importLock)
          {
            libraryImportScan();
            while (currentlyImporting)
            {
              try { importLock.wait(0);}catch(InterruptedException e){}
            }
          }
        }
      }

}
