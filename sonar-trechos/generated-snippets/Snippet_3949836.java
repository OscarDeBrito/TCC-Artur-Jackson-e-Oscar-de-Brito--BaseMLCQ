public final class ResultImpl implements AtlasIndexQuery.Result<AtlasJanusVertex, AtlasJanusEdge> {
        private JanusGraphIndexQuery.Result<JanusGraphVertex> source;

        public ResultImpl(JanusGraphIndexQuery.Result<JanusGraphVertex> source) {
            this.source = source;
        }

        @Override
        public AtlasVertex<AtlasJanusVertex, AtlasJanusEdge> getVertex() {
            return GraphDbObjectFactory.createVertex(graph, source.getElement());
        }

        @Override
        public double getScore() {
            return source.getScore();
        }
    }
