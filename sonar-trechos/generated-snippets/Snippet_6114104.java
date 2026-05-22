public static class SynchronizedRichIterableSerializationProxy<T> implements Externalizable
    {
        private static final long serialVersionUID = 1L;

        private RichIterable<T> richIterable;

        @SuppressWarnings("UnusedDeclaration")
        public SynchronizedRichIterableSerializationProxy()
        {
            // Empty constructor for Externalizable class
        }

        public SynchronizedRichIterableSerializationProxy(RichIterable<T> iterable)
        {
            this.richIterable = iterable;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(this.richIterable);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            this.richIterable = (RichIterable<T>) in.readObject();
        }

        protected Object readResolve()
        {
            return new SynchronizedRichIterable<>(this.richIterable);
        }
    }
