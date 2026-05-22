public final class HostsSupportImpl {

    public static final String LOCALHOST_PROPERTIES_FILENAME = "localhost" + Storage.DEFAULT_PROPERTIES_EXT; // NOI18N

    private static final String HOSTS_STORAGE_DIRNAME = "hosts";    // NOI18N
    private static final Object hostsStorageDirectoryStringLock = new Object();
    // @GuardedBy hostsStorageDirectoryStringLock
    private static String hostsStorageDirectoryString;


    public static String getStorageDirectoryString() {
        synchronized(hostsStorageDirectoryStringLock) {
            if (hostsStorageDirectoryString == null)
                hostsStorageDirectoryString = Storage.getPersistentStorageDirectoryString() +
                        File.separator + HOSTS_STORAGE_DIRNAME;
            return hostsStorageDirectoryString;
        }
    }

}
