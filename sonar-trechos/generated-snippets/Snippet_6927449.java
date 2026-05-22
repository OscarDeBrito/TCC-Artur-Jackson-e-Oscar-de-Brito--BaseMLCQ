public class IssueCommentsFeed extends BaseFeed<IssueCommentsFeed,
    IssueCommentsEntry> {

  /**
   * Default mutable constructor.
   */
  public IssueCommentsFeed() {
    super(IssueCommentsEntry.class);
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link BaseFeed} instance.
   *
   * @param sourceFeed source feed
   */
  public IssueCommentsFeed(BaseFeed<?, ?> sourceFeed) {
    super(IssueCommentsEntry.class, sourceFeed);
  }

  @Override
  public void declareExtensions(ExtensionProfile extProfile) {
    if (extProfile.isDeclared(IssueCommentsFeed.class)) {
      return;
    }
    super.declareExtensions(extProfile);
    extProfile.declare(IssueCommentsFeed.class,
        IssuesLink.getDefaultDescription(true, true));
  }

  @Override
  public String toString() {
    return "{IssueCommentsFeed " + super.toString() + "}";
  }

}
