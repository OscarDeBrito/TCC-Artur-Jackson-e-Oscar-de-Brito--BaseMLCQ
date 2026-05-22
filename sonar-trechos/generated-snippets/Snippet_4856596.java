public class Snippet__4856596 {

    public boolean setNodeReferences(ChangeLog references) {
            try {
                ChangeLog log = new ChangeLog();

                for (NodeReferences source : references.modifiedRefs()) {
                    // filter out version storage intern ones
                    NodeReferences target = new NodeReferences(source.getTargetId());
                    for (PropertyId id : source.getReferences()) {
                        if (!hasNonVirtualItemState(id.getParentId())) {
                            target.addReference(id);
                        }
                    }
                    log.modified(target);
                }

                if (log.hasUpdates()) {
                    pMgr.store(log);
                }
                return true;
            } catch (ItemStateException e) {
                log.error("Error while setting references: " + e.toString());
                return false;
            }
        }

}
