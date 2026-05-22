public final class GssInitCred extends GssCredElement {

    private KerberosTicket ticket;
    private KrbToken krbToken;

    private GssInitCred(GSSCaller caller, GssNameElement name,
                        KerberosTicket ticket, KrbToken krbToken, int lifeTime) {
        super(caller, name);
        this.ticket = ticket;
        this.initLifeTime = lifeTime;
        this.krbToken = krbToken;
    }

    public static GssInitCred getInstance(GSSCaller caller, GssNameElement name, int lifeTime) throws GSSException {
        Set<KrbToken> krbTokens = CredUtils.getContextCredentials(KrbToken.class);
        KrbToken krbToken = krbTokens != null && !krbTokens.isEmpty() ? krbTokens.iterator().next() : null;

        if (name == null) {
            KerberosTicket ticket = CredUtils.getKerberosTicketFromContext(caller, null, null);
            GssNameElement clientName = GssNameElement.getInstance(ticket.getClient().getName(), GSSName.NT_USER_NAME);
            return new GssInitCred(caller, clientName, ticket, krbToken, lifeTime);
        }

        KerberosTicket ticket = CredUtils.getKerberosTicketFromContext(caller, name.getPrincipalName().getName(), null);
        return new GssInitCred(caller, name, ticket, krbToken, lifeTime);
    }

    public boolean isInitiatorCredential() throws GSSException {
        return true;
    }

    public boolean isAcceptorCredential() throws GSSException {
        return false;
    }

    public KerberosTicket getKerberosTicket() {
        return ticket;
    }

    public KrbToken getKrbToken() {
        return krbToken;
    }
}
