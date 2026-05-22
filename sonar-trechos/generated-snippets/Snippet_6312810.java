@KapuaProvider
public class JobExecutionFactoryImpl implements JobExecutionFactory {

    @Override
    public JobExecution newEntity(KapuaId scopeId) {
        return new JobExecutionImpl(scopeId);
    }

    @Override
    public JobExecutionCreator newCreator(KapuaId scopeId) {
        return new JobExecutionCreatorImpl(scopeId);
    }

    @Override
    public JobExecutionQuery newQuery(KapuaId scopeId) {
        return new JobExecutionQueryImpl(scopeId);
    }

    @Override
    public JobExecutionListResult newListResult() {
        return new JobExecutionListResultImpl();
    }

}
