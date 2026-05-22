public class Snippet__6289735 {

    @ManagedAttribute(value = "idletimeout in MS", readonly = true)
        public long getIdleTimeoutMs()
        {
            if(_idleTimeUnit==null)
                return 0;
            return _idleTimeUnit.toMillis(_idleTime);
        }

}
