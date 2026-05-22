public static class ReferenceMap {
    /**
     * Used to indicate whether the reference node itself has been saved
     */
    private final Map<Long, INodeReference.WithCount> referenceMap
        = new HashMap<Long, INodeReference.WithCount>();
    /**
     * Used to record whether the subtree of the reference node has been saved 
     */
    private final Map<Long, Long> dirMap = new HashMap<Long, Long>();

    public void writeINodeReferenceWithCount(
        INodeReference.WithCount withCount, DataOutput out,
        boolean writeUnderConstruction) throws IOException {
      final INode referred = withCount.getReferredINode();
      final long id = withCount.getId();
      final boolean firstReferred = !referenceMap.containsKey(id);
      out.writeBoolean(firstReferred);

      if (firstReferred) {
        FSImageSerialization.saveINode2Image(referred, out,
            writeUnderConstruction, this);
        referenceMap.put(id, withCount);
      } else {
        out.writeLong(id);
      }
    }
    
    public boolean toProcessSubtree(long id) {
      if (dirMap.containsKey(id)) {
        return false;
      } else {
        dirMap.put(id, id);
        return true;
      }
    }
    
    public INodeReference.WithCount loadINodeReferenceWithCount(
        boolean isSnapshotINode, DataInput in, FSImageFormat.Loader loader
        ) throws IOException {
      final boolean firstReferred = in.readBoolean();

      final INodeReference.WithCount withCount;
      if (firstReferred) {
        final INode referred = loader.loadINodeWithLocalName(isSnapshotINode,
            in, true);
        withCount = new INodeReference.WithCount(null, referred);
        referenceMap.put(withCount.getId(), withCount);
      } else {
        final long id = in.readLong();
        withCount = referenceMap.get(id);
      }
      return withCount;
    }
  }
