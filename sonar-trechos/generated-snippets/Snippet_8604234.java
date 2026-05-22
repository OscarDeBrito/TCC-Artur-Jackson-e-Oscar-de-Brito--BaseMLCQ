private static class T3 implements Externalizable {
        /** */
        private static final long serialVersionUID = 0L;

        /** */
        private GridTopic topic;

        /** */
        private UUID id1;

        /**
         * No-arg constructor needed for {@link Serializable}.
         */
        public T3() {
            // No-op.
        }

        /**
         * @param topic Topic.
         * @param id1 ID1.
         */
        private T3(GridTopic topic, UUID id1) {
            this.topic = topic;
            this.id1 = id1;
        }

        /** {@inheritDoc} */
        @Override public int hashCode() {
            return topic.ordinal() + id1.hashCode();
        }

        /** {@inheritDoc} */
        @Override public boolean equals(Object obj) {
            if (obj.getClass() == T3.class) {
                T3 that = (T3)obj;

                return topic == that.topic && id1.equals(that.id1);
            }

            return false;
        }

        /** {@inheritDoc} */
        @Override public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(topic.ordinal());
            U.writeUuid(out, id1);
        }

        /** {@inheritDoc} */
        @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            topic = fromOrdinal(in.readByte());
            id1 = U.readUuid(in);
        }

        /** {@inheritDoc} */
        @Override public String toString() {
            return S.toString(T3.class, this);
        }
    }
