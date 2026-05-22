public static interface OrdinalMap {
    /**
     * Set the size of the map. This MUST be called before addMapping().
     * It is assumed (but not verified) that addMapping() will then be
     * called exactly 'size' times, with different origOrdinals between 0
     * and size-1.  
     */
    public void setSize(int size) throws IOException;

    /** Record a mapping. */
    public void addMapping(int origOrdinal, int newOrdinal) throws IOException;

    /**
     * Call addDone() to say that all addMapping() have been done.
     * In some implementations this might free some resources. 
     */
    public void addDone() throws IOException;

    /**
     * Return the map from the taxonomy's original (consecutive) ordinals
     * to the new taxonomy's ordinals. If the map has to be read from disk
     * and ordered appropriately, it is done when getMap() is called.
     * getMap() should only be called once, and only when the map is actually
     * needed. Calling it will also free all resources that the map might
     * be holding (such as temporary disk space), other than the returned int[].
     */
    public int[] getMap() throws IOException;
  }
