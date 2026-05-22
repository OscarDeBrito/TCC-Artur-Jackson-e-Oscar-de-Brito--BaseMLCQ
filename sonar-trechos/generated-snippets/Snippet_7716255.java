public interface IMutableMembershipSet {
    /**
     * Add this value to the membership set.
     */
    void add(int index);

    /**
     * Finish updating.
     * @return  A membership set that can be used in read mode.
     */
    IMembershipSet seal();

    /**
     * The number of elements currently in the set.
     */
    int size();
}
