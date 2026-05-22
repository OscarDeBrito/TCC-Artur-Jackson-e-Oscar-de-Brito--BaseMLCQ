public class DestroyCommand extends StorageCommand {
    // in VMware, things are designed around VM instead of volume, we need it the volume VM context if the volume is attached
    String vmName;
    VolumeTO volume;

    protected DestroyCommand() {
    }

    public DestroyCommand(StoragePool pool, Volume volume, String vmName) {
        this.volume = new VolumeTO(volume, pool);
        this.vmName = vmName;
    }

    public DestroyCommand(StoragePool pool, VMTemplateStorageResourceAssoc templatePoolRef) {
        volume =
            new VolumeTO(templatePoolRef.getId(), null, pool.getPoolType(), pool.getUuid(), null, pool.getPath(), templatePoolRef.getInstallPath(),
                templatePoolRef.getTemplateSize(), null);
    }

    public VolumeTO getVolume() {
        return volume;
    }

    public String getVmName() {
        return vmName;
    }

    @Override
    public boolean executeInSequence() {
        return true;
    }
}
