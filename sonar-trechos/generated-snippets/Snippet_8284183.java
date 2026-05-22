public interface MailboxAnnotationManager {

    /**
     * Return all mailbox's annotation as the {@link List} of {@link MailboxAnnotation} without order and
     * do not contain any two annotations with the same key
     * 
     * @param mailboxPath   the current mailbox
     * @param session       the current session
     * @return              List<MailboxAnnotation>
     * @throws MailboxException in case of selected mailbox does not exist
     */
    List<MailboxAnnotation> getAllAnnotations(MailboxPath mailboxPath, MailboxSession session) throws MailboxException;

    /**
     * Return all mailbox's annotation filter by the list of the keys without order and
     * do not contain any two annotations with the same key
     * 
     * @param mailboxPath   the current mailbox
     * @param session       the current session
     * @param keys          list of the keys should be filter
     * @return              List<MailboxAnnotation>
     * @throws MailboxException in case of selected mailbox does not exist
     */
    List<MailboxAnnotation> getAnnotationsByKeys(MailboxPath mailboxPath, MailboxSession session, Set<MailboxAnnotationKey> keys) throws MailboxException;

    /**
     * Return all mailbox's annotation by the list of the keys and its children entries without order and
     * do not contain any two annotations with the same key
     *
     * @param mailboxPath   the current mailbox
     * @param session       the current session
     * @param keys          list of the keys should be filter
     * @return              List<MailboxAnnotation>
     * @throws MailboxException in case of selected mailbox does not exist
     */
    List<MailboxAnnotation> getAnnotationsByKeysWithOneDepth(MailboxPath mailboxPath, MailboxSession session, Set<MailboxAnnotationKey> keys) throws MailboxException;

    /**
     * Return all mailbox's annotation by the list of the keys and its below entries without order and
     * do not contain any two annotations with the same key
     *
     * @param mailboxPath   the current mailbox
     * @param session       the current session
     * @param keys          list of the keys should be filter
     * @return              List<MailboxAnnotation>
     * @throws MailboxException in case of selected mailbox does not exist
     */
    List<MailboxAnnotation> getAnnotationsByKeysWithAllDepth(MailboxPath mailboxPath, MailboxSession session, Set<MailboxAnnotationKey> keys) throws MailboxException;

    /**
     * Update the mailbox's annotations. This method can:
     * - Insert new annotation if it does not exist
     * - Update the new value for existed annotation
     * - Delete the existed annotation if its value is nil
     * 
     * @param mailboxPath   the current mailbox
     * @param session       the current session
     * @param mailboxAnnotations    the list of annotation should be insert/udpate/delete
     * @throws MailboxException in case of selected mailbox does not exist
     */
    void updateAnnotations(MailboxPath mailboxPath, MailboxSession session, List<MailboxAnnotation> mailboxAnnotations) throws MailboxException, AnnotationException;
}
