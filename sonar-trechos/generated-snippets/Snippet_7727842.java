public class Snippet__7727842 {

    public void selectOwner(String selectorPath, String key, Operation op) {
            if (isStopping()) {
                op.fail(new CancellationException("Host is stopping"));
                return;
            }

            SelectAndForwardRequest body = this.selectOwnerRequests.get();
            body.key = key;

            NodeSelectorService nss = findNodeSelectorService(selectorPath, op);
            if (nss == null) {
                return;
            }

            nss.selectAndForward(op, body);
        }

}
