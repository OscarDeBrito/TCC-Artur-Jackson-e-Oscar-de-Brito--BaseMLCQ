public class SavingsAccountSheetPopulator extends AbstractWorkbookPopulator {

   private List<SavingsAccountData> savingsAccountDataList;
   private Map<ClientData,List<SavingsAccountData>> clientToSavingsMap;

   private static final int SAVINGS_ACCOUNT_ID_COL=0;
   private static final int SAVING_ACCOUNT_NO=1;
   private static final int CURRENCY_COL=2;
   private static final int CLIENT_NAME=3;


    public SavingsAccountSheetPopulator(List<SavingsAccountData> savingsAccountDataList) {
        this.savingsAccountDataList=savingsAccountDataList;
    }

    @Override
    public void populate(Workbook workbook,String dateFormat) {
        Sheet savingsSheet=workbook.createSheet(TemplatePopulateImportConstants.SAVINGS_ACCOUNTS_SHEET_NAME);
        setLayout(savingsSheet);
        populateSavingsSheet(savingsSheet);
        savingsSheet.protectSheet("");
    }

    private void populateSavingsSheet(Sheet savingsSheet) {
        int rowIndex=1;
        for (SavingsAccountData savings: savingsAccountDataList) {
            Row row=savingsSheet.createRow(rowIndex++);
            writeLong(SAVINGS_ACCOUNT_ID_COL,row,savings.id());
            writeString(SAVING_ACCOUNT_NO,row,savings.getAccountNo());
            writeString(CURRENCY_COL,row,savings.currency().code());
            writeString(CLIENT_NAME,row,savings.getClientName());
        }
    }


    private void setLayout(Sheet savingsSheet) {
        Row rowHeader = savingsSheet.createRow(TemplatePopulateImportConstants.ROWHEADER_INDEX);
        rowHeader.setHeight(TemplatePopulateImportConstants.ROW_HEADER_HEIGHT);

        savingsSheet.setColumnWidth(SAVINGS_ACCOUNT_ID_COL,TemplatePopulateImportConstants.MEDIUM_COL_SIZE);
        writeString(SAVINGS_ACCOUNT_ID_COL,rowHeader,"Savings Account Id");

        savingsSheet.setColumnWidth(SAVING_ACCOUNT_NO,TemplatePopulateImportConstants.MEDIUM_COL_SIZE);
        writeString(SAVING_ACCOUNT_NO,rowHeader,"Savings Account No");

        savingsSheet.setColumnWidth(CURRENCY_COL,TemplatePopulateImportConstants.SMALL_COL_SIZE);
        writeString(CURRENCY_COL,rowHeader,"Currency Code");

        savingsSheet.setColumnWidth(CLIENT_NAME,TemplatePopulateImportConstants.SMALL_COL_SIZE);
        writeString(CLIENT_NAME,rowHeader,"Client Name");

    }
}
