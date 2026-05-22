public static class Elevated {
      private static final int MOVE_FILE = 1;
      private static final int COPY_FILE = 2;

      public static void mkdir(Path dirName) throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for mkdir");
        }
        elevatedMkDirImpl(dirName.toString());
      }
      
      private static native void elevatedMkDirImpl(String dirName) 
          throws IOException;
      
      public static void chown(Path fileName, String user, String group) 
          throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for chown");
        }
        elevatedChownImpl(fileName.toString(), user, group);
      }
      
      private static native void elevatedChownImpl(String fileName, String user, 
          String group) throws IOException;
      
      public static void move(Path src, Path dst, boolean replaceExisting) 
          throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for move");
        }
        elevatedCopyImpl(MOVE_FILE, src.toString(), dst.toString(), 
            replaceExisting);
      }
      
      public static void copy(Path src, Path dst, boolean replaceExisting) 
          throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for copy");
        }
        elevatedCopyImpl(COPY_FILE, src.toString(), dst.toString(), 
            replaceExisting);
      }
      
      private static native void elevatedCopyImpl(int operation, String src, 
          String dst, boolean replaceExisting) throws IOException;
      
      public static void chmod(Path fileName, int mode) throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for chmod");
        }
        elevatedChmodImpl(fileName.toString(), mode);
      }
      
      private static native void elevatedChmodImpl(String path, int mode) 
          throws IOException;
      
      public static void killTask(String containerName) throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for killTask");
        }
        elevatedKillTaskImpl(containerName);
      }
      
      private static native void elevatedKillTaskImpl(String containerName) 
          throws IOException;

      public static OutputStream create(Path f, boolean append)  
          throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for create");
        }
        
        long desiredAccess = Windows.GENERIC_WRITE;
        long shareMode = 0L;
        long creationDisposition = append ? 
            Windows.OPEN_ALWAYS : Windows.CREATE_ALWAYS;
        long flags = Windows.FILE_ATTRIBUTE_NORMAL;
        
        String fileName = f.toString();
        fileName = fileName.replace('/', '\\');
        
        long hFile = elevatedCreateImpl(
            fileName, desiredAccess, shareMode, creationDisposition, flags);
        return new FileOutputStream(
            WinutilsProcessStub.getFileDescriptorFromHandle(hFile));
      }
      
      private static native long elevatedCreateImpl(String path, 
          long desiredAccess, long shareMode,
          long creationDisposition, long flags) throws IOException;
      
      
      public static boolean deleteFile(Path path) throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for deleteFile");
        }
        
        return elevatedDeletePathImpl(path.toString(), false);
      }

      public static boolean deleteDirectory(Path path) throws IOException {
        if (!nativeLoaded) {
          throw new IOException("Native WSCE libraries are required for deleteDirectory");
        }
        
        return elevatedDeletePathImpl(path.toString(), true);
      }

      public native static boolean elevatedDeletePathImpl(String path, 
          boolean isDir) throws IOException;
    }
