public class JPAModSeqProvider extends AbstractLockingModSeqProvider {

    private final EntityManagerFactory factory;

    @Inject
    public JPAModSeqProvider(MailboxPathLocker locker, EntityManagerFactory factory) {
        super(locker);
        this.factory = factory;
    }

    @Override
    public long highestModSeq(MailboxSession session, Mailbox mailbox) throws MailboxException {
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            JPAId mailboxId = (JPAId) mailbox.getMailboxId();
            long highest = (Long) manager.createNamedQuery("findHighestModSeq").setParameter("idParam", mailboxId.getRawId()).getSingleResult();
            manager.getTransaction().commit();
            return highest;
        } catch (PersistenceException e) {
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new MailboxException("Unable to get highest mod-sequence for mailbox " + mailbox, e);
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    protected long lockedNextModSeq(MailboxSession session, Mailbox mailbox) throws MailboxException {
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            JPAId mailboxId = (JPAId) mailbox.getMailboxId();
            JPAMailbox m = manager.find(JPAMailbox.class, mailboxId.getRawId());
            long modSeq = m.consumeModSeq();
            manager.persist(m);
            manager.getTransaction().commit();
            return modSeq;
        } catch (PersistenceException e) {
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new MailboxException("Unable to save highest mod-sequence for mailbox " + mailbox, e);
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public long highestModSeq(MailboxSession session, MailboxId mailboxId) throws MailboxException {
        throw new NotImplementedException("not implemented");
    }
}
