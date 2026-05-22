interface BookieWatcher {
    Set<BookieSocketAddress> getBookies() throws BKException;
    Set<BookieSocketAddress> getReadOnlyBookies() throws BKException;

    /**
     * Create an ensemble with given <i>ensembleSize</i> and <i>writeQuorumSize</i>.
     *
     * @param ensembleSize
     *          Ensemble Size
     * @param writeQuorumSize
     *          Write Quorum Size
     * @return list of bookies for new ensemble.
     * @throws BKNotEnoughBookiesException
     */
    List<BookieSocketAddress> newEnsemble(int ensembleSize, int writeQuorumSize,
                                          int ackQuorumSize, Map<String, byte[]> customMetadata)
            throws BKNotEnoughBookiesException;

    /**
     * Choose a bookie to replace bookie <i>bookieIdx</i> in <i>existingBookies</i>.
     * @param existingBookies
     *          list of existing bookies.
     * @param bookieIdx
     *          index of the bookie in the list to be replaced.
     * @return the bookie to replace.
     * @throws BKNotEnoughBookiesException
     */
    BookieSocketAddress replaceBookie(int ensembleSize, int writeQuorumSize, int ackQuorumSize,
                                      Map<String, byte[]> customMetadata,
                                      List<BookieSocketAddress> existingBookies, int bookieIdx,
                                      Set<BookieSocketAddress> excludeBookies)
            throws BKNotEnoughBookiesException;


    /**
     * Quarantine <i>bookie</i> so it will not be preferred to be chosen for new ensembles.
     * @param bookie
     */
    void quarantineBookie(BookieSocketAddress bookie);
}
