public class Snippet__4303100 {

    public static void validate(EncryptionKey encKey, ApRep apRep, ApReq apReqSent) throws KrbException {
            EncAPRepPart encPart = EncryptionUtil.unseal(apRep.getEncryptedEncPart(),
                    encKey, KeyUsage.AP_REP_ENCPART, EncAPRepPart.class);
            apRep.setEncRepPart(encPart);
            if (apReqSent != null) {
                Authenticator auth = apReqSent.getAuthenticator();
                if (!encPart.getCtime().equals(auth.getCtime())
                        || encPart.getCusec() != auth.getCusec()) {
                    throw new KrbException(KrbErrorCode.KRB_AP_ERR_MUT_FAIL);
                }
            }
        }

}
