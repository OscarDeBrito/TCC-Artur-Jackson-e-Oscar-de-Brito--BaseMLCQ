public class Snippet__4045338 {

    private static void insertInitialDataverses(MetadataTransactionContext mdTxnCtx) throws AlgebricksException {
            String dataFormat = NonTaggedDataFormat.NON_TAGGED_DATA_FORMAT;
            MetadataManager.INSTANCE.addDataverse(mdTxnCtx,
                    new Dataverse(MetadataConstants.METADATA_DATAVERSE_NAME, dataFormat, MetadataUtil.PENDING_NO_OP));
            MetadataManager.INSTANCE.addDataverse(mdTxnCtx, MetadataBuiltinEntities.DEFAULT_DATAVERSE);
        }

}
