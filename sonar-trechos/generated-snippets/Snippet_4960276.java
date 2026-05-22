private class SinkNodeFactory<K, V> extends NodeFactory {
        private final Serializer<K> keySerializer;
        private final Serializer<V> valSerializer;
        private final StreamPartitioner<? super K, ? super V> partitioner;
        private final TopicNameExtractor<K, V> topicExtractor;

        private SinkNodeFactory(final String name,
                                final String[] predecessors,
                                final TopicNameExtractor<K, V> topicExtractor,
                                final Serializer<K> keySerializer,
                                final Serializer<V> valSerializer,
                                final StreamPartitioner<? super K, ? super V> partitioner) {
            super(name, predecessors.clone());
            this.topicExtractor = topicExtractor;
            this.keySerializer = keySerializer;
            this.valSerializer = valSerializer;
            this.partitioner = partitioner;
        }

        @Override
        public ProcessorNode build() {
            if (topicExtractor instanceof StaticTopicNameExtractor) {
                final String topic = ((StaticTopicNameExtractor) topicExtractor).topicName;
                if (internalTopicNames.contains(topic)) {
                    // prefix the internal topic name with the application id
                    return new SinkNode<>(name, new StaticTopicNameExtractor<>(decorateTopic(topic)), keySerializer, valSerializer, partitioner);
                } else {
                    return new SinkNode<>(name, topicExtractor, keySerializer, valSerializer, partitioner);
                }
            } else {
                return new SinkNode<>(name, topicExtractor, keySerializer, valSerializer, partitioner);
            }
        }

        @Override
        Sink describe() {
            return new Sink(name, topicExtractor);
        }
    }
