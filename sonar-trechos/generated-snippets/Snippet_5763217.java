public class Snippet__5763217 {

    @Override
            public String call() throws Exception {
                ThreadContext.setApplication(application);
                ThreadContext.setSession(session);

                try {
                    final ResourceTO resource = resourceRestClient.read(key);
                    return String.format("{ \"status\": \"%s\", \"target\": \"%s\"}",
                            resourceRestClient.check(resource).getLeft()
                            ? TopologyNode.Status.REACHABLE : TopologyNode.Status.UNREACHABLE, key);
                } catch (Exception e) {
                    LOG.warn("Error checking connection for {}", key, e);
                    return String.format("{ \"status\": \"%s\", \"target\": \"%s\"}",
                            TopologyNode.Status.FAILURE,
                            key);
                } finally {
                    ThreadContext.detach();
                }
            }

}
