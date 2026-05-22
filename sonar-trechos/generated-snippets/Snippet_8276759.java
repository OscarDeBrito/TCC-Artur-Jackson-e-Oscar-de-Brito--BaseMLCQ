public class Snippet__8276759 {

    private Mono<ModSeq> tryUpdateModSeq(CassandraId mailboxId, ModSeq modSeq) {
            ModSeq nextModSeq = modSeq.next();
            return cassandraAsyncExecutor.executeReturnApplied(
                update.bind()
                    .setUUID(MAILBOX_ID, mailboxId.asUuid())
                    .setLong(NEXT_MODSEQ, nextModSeq.getValue())
                    .setLong(MOD_SEQ_CONDITION, modSeq.getValue()))
                .flatMap(success -> successToModSeq(nextModSeq, success));
        }

}
