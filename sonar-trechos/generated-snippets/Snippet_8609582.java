public class AlignedBuffersDirectFileIOFactory implements FileIOFactory {
    /** Logger. */
    private final IgniteLogger log;

    /** Page size from durable memory. */
    private final int pageSize;

    /** Backup factory for files in case native is not available or not applicable. */
    private final FileIOFactory backupFactory;

    /** File system/os block size, negative value if library init was failed. */
    private final int ioBlockSize;

    /** Use backup factory, {@code true} if direct IO setup failed. */
    private boolean useBackupFactory;

    /** Thread local with buffers with capacity = one page {@code pageSize} and aligned using {@code fsBlockSize}. */
    private ThreadLocal<ByteBuffer> tlbOnePageAligned;

    /**
     * Managed aligned buffers. This collection is used to free buffers, an for checking if buffer is known to be
     * already aligned.
     */
    private final ConcurrentHashMap<Long, Thread> managedAlignedBuffers = new ConcurrentHashMap<>();

    /**
     * Creates direct native IO factory.
     *
     * @param log Logger.
     * @param storePath Storage path, used to check FS settings.
     * @param pageSize durable memory page size.
     * @param backupFactory fallback factory if init failed.
     */
    public AlignedBuffersDirectFileIOFactory(
        final IgniteLogger log,
        final File storePath,
        final int pageSize,
        final FileIOFactory backupFactory) {
        this.log = log;
        this.pageSize = pageSize;
        this.backupFactory = backupFactory;

        useBackupFactory = true;
        ioBlockSize = IgniteNativeIoLib.getDirectIOBlockSize(storePath.getAbsolutePath(), log);

        if(!IgniteSystemProperties.getBoolean(IgniteSystemProperties.IGNITE_DIRECT_IO_ENABLED, true)) {
            if (log.isInfoEnabled())
                log.info("Direct IO is explicitly disabled by system property.");

            return;
        }

        if (ioBlockSize > 0) {
            int blkSize = ioBlockSize;

            if (pageSize % blkSize != 0) {
                U.warn(log, String.format("Unable to setup Direct IO for Ignite [pageSize=%d bytes;" +
                        " file system block size=%d]. For speeding up Ignite consider setting %s.setPageSize(%d)." +
                        " Direct IO is disabled.",
                    pageSize, blkSize, DataStorageConfiguration.class.getSimpleName(), blkSize));
            }
            else {
                useBackupFactory = false;

                tlbOnePageAligned = new ThreadLocal<ByteBuffer>() {
                    @Override protected ByteBuffer initialValue() {
                        return createManagedBuffer(pageSize);
                    }
                };

                if (log.isInfoEnabled()) {
                    log.info(String.format("Direct IO is enabled for block IO operations on aligned memory structures." +
                        " [block size = %d, durable memory page size = %d]", blkSize, pageSize));
                }
            }
        }
        else {
            if (log.isInfoEnabled()) {
                log.info(String.format("Direct IO library is not available on current operating system [%s]." +
                    " Direct IO is not enabled.", System.getProperty("os.version")));
            }
        }

    }

    /**
     * <b>Note: </b> Use only if {@link #isDirectIoAvailable()}.
     *
     * @param size buffer size to allocate.
     * @return new byte buffer.
     */
    @NotNull ByteBuffer createManagedBuffer(int size) {
        assert !useBackupFactory : "Direct IO is disabled, aligned managed buffer creation is disabled now";
        assert managedAlignedBuffers != null : "Direct buffers not available";

        ByteBuffer allocate = AlignedBuffers.allocate(ioBlockSize, size).order(ByteOrder.nativeOrder());

        managedAlignedBuffers.put(GridUnsafe.bufferAddress(allocate), Thread.currentThread());

        return allocate;
    }

    /** {@inheritDoc} */
    @Override public FileIO create(File file, OpenOption... modes) throws IOException {
        if (useBackupFactory)
            return backupFactory.create(file, modes);

        return new AlignedBuffersDirectFileIO(ioBlockSize, pageSize, file, modes, tlbOnePageAligned, managedAlignedBuffers, log);

    }

    /**
     * @return {@code true} if Direct IO can be used on current OS and file system settings
     */
    boolean isDirectIoAvailable() {
        return !useBackupFactory;
    }

    /**
     * Managed aligned buffers and its associated threads. This collection is used to free buffers, an for checking if
     * buffer is known to be already aligned.
     *
     * @return map address->thread.
     */
    ConcurrentHashMap<Long, Thread> managedAlignedBuffers() {
        return managedAlignedBuffers;
    }
}
