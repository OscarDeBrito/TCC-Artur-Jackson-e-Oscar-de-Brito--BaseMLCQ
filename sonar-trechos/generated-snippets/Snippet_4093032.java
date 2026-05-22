public class CountRetryPolicyFactory implements IRetryPolicyFactory {

    private final int count;

    public CountRetryPolicyFactory(int count) {
        this.count = count;
    }

    @Override
    public IRetryPolicy create(IActiveEntityEventsListener listener) {
        return new CountRetryPolicy(count);
    }

}
