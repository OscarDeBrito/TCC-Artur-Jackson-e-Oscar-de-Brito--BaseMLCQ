public interface ITagTreeManagerListener {
  /**
   * Invoked after a tag was added to the manager.
   * 
   * @param manager The tag manager the tag was added to.
   * @param tag The new tag.
   */
  void addedTag(ITagTreeManager manager, ITreeNode<CTag> tag);

  /**
   * Invoked after a tag was deleted from the tag manager.
   * 
   * Note that usage of the deleted tag after this method was invoked leads to undefined behavior.
   * 
   * @param manager The tag manager from which the tag was removed.
   * @param tag The tag that was removed from the tag manager.
   * @param parent The parent tag.
   */
  void deletedTag(ITagTreeManager manager, ITreeNode<CTag> parent, ITreeNode<CTag> tag);

  /**
   * Invoked after a whole subtree of a tag manager's tag tree was deleted.
   * 
   * Note that using any of the deleted tags after this method was invoked leads to undefined
   * behavior.
   * 
   * @param manager The tag manager from which the subtree was removed.
   * @param parent The parent tag.
   * @param tag The root tag of the subtree that was removed from the tag manager.
   */
  void deletedTagSubtree(ITagTreeManager manager, ITreeNode<CTag> parent, ITreeNode<CTag> tag);

  /**
   * Invoked after a new tag was inserted into a tag manager.
   * 
   * @param manager The tag manager where the new tag was inserted.
   * @param parent The parent tag of the new tag.
   * @param tag The tag that was added to the tag manager.
   */
  void insertedTag(ITagTreeManager manager, ITreeNode<CTag> parent, ITreeNode<CTag> tag);
}
