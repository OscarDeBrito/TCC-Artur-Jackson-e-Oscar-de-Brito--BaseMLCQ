public interface VolumeHostDao extends GenericDao<VolumeHostVO, Long>,
        StateDao<ObjectInDataStoreStateMachine.State, ObjectInDataStoreStateMachine.Event, DataObjectInStore> {

    VolumeHostVO findByHostVolume(long hostId, long volumeId);

    VolumeHostVO findByVolumeId(long volumeId);

    List<VolumeHostVO> listBySecStorage(long sserverId);

    List<VolumeHostVO> listDestroyed(long hostId);

    VolumeHostVO findVolumeByZone(long zoneId, long volumeId);

}
