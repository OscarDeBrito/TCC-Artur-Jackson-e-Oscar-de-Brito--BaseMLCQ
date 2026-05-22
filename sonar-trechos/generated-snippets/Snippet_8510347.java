@ResourceWrapper(handles =  CreateCommand.class)
public final class LibvirtCreateCommandWrapper extends CommandWrapper<CreateCommand, Answer, LibvirtComputingResource> {

    private static final Logger s_logger = Logger.getLogger(LibvirtCreateCommandWrapper.class);

    @Override
    public Answer execute(final CreateCommand command, final LibvirtComputingResource libvirtComputingResource) {
        final StorageFilerTO pool = command.getPool();
        final DiskProfile dskch = command.getDiskCharacteristics();
        KVMPhysicalDisk baseVol = null;
        KVMStoragePool primaryPool = null;
        KVMPhysicalDisk vol = null;
        long disksize;
        try {
            final KVMStoragePoolManager storagePoolMgr = libvirtComputingResource.getStoragePoolMgr();
            primaryPool = storagePoolMgr.getStoragePool(pool.getType(), pool.getUuid());
            disksize = dskch.getSize();

            if (command.getTemplateUrl() != null) {
                if (primaryPool.getType() == StoragePoolType.CLVM) {
                    vol = libvirtComputingResource.templateToPrimaryDownload(command.getTemplateUrl(), primaryPool, dskch.getPath());
                } else {
                    baseVol = primaryPool.getPhysicalDisk(command.getTemplateUrl());
                    vol = storagePoolMgr.createDiskFromTemplate(baseVol, dskch.getPath(), dskch.getProvisioningType(), primaryPool, baseVol.getSize(), 0);
                }
                if (vol == null) {
                    return new Answer(command, false, " Can't create storage volume on storage pool");
                }
            } else {
                vol = primaryPool.createPhysicalDisk(dskch.getPath(), dskch.getProvisioningType(), dskch.getSize());
                if (vol == null) {
                    return new Answer(command, false, " Can't create Physical Disk");
                }
            }
            final VolumeTO volume = new VolumeTO(command.getVolumeId(), dskch.getType(), pool.getType(), pool.getUuid(), pool.getPath(), vol.getName(), vol.getName(), disksize,
                    null);

            volume.setBytesReadRate(dskch.getBytesReadRate());
            volume.setBytesWriteRate(dskch.getBytesWriteRate());
            volume.setIopsReadRate(dskch.getIopsReadRate());
            volume.setIopsWriteRate(dskch.getIopsWriteRate());
            volume.setCacheMode(dskch.getCacheMode());
            return new CreateAnswer(command, volume);
        } catch (final CloudRuntimeException e) {
            s_logger.debug("Failed to create volume: " + e.toString());
            return new CreateAnswer(command, e);
        }
    }
}
