@Beta
public interface PersistenceExceptionHandler {

    void stop();

    void onGenerateMementoFailed(BrooklynObjectType type, BrooklynObject instance, Exception e);
    
    void onPersistMementoFailed(Memento memento, Exception e);
    
    void onPersistRawMementoFailed(BrooklynObjectType type, String id, Exception e);

    void onDeleteMementoFailed(String id, Exception e);
    
    void onUpdatePlaneIdFailed(String planeId, Exception e);

}
