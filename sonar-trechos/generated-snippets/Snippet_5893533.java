public class Snippet__5893533 {

    @Override
        public Collection<Class> getMessageTypes() {
            return new ArrayList() {{
                add( QueueGetRequest.class );
                add( QueueInitRequest.class );
                add( QueueRefreshRequest.class );
                add( QueueTimeoutRequest.class );
                add( ShardCheckRequest.class );
            }};
        }

}
