public class SingleItemIterator implements Iterator<T>
    {
        private T instance;

        public SingleItemIterator(T instance)
        {
            this.instance = instance;
        }

        @Override
        public boolean hasNext()
        {
            return instance != null;
        }

        @Override
        public T next()
        {
            T oldInstance = instance;
            this.instance = null;
            return oldInstance;
        }

        @Override
        public void remove()
        {
            // do nothing...
        }
    }
