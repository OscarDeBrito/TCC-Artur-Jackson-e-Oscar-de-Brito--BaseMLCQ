public class TransactionalStateStorageZkFactory implements ITransactionalStateStorageFactory {
    @Override
    public ITransactionalStateStorage mkTransactionalState(Map conf, String id, String subroot) {
        return new TransactionalStateZkStorage(conf, id, subroot);
    }
}
