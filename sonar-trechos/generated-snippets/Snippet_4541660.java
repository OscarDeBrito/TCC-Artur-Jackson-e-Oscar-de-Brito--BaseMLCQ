public class Snippet__4541660 {

    @Override
        public void populate(Workbook workbook,String dateFormat) {
            Sheet savingsTransactionSheet = workbook.createSheet(TemplatePopulateImportConstants.SAVINGS_TRANSACTION_SHEET_NAME);
            setLayout(savingsTransactionSheet);
            officeSheetPopulator.populate(workbook,dateFormat);
            clientSheetPopulator.populate(workbook,dateFormat);
            extrasSheetPopulator.populate(workbook,dateFormat);
            populateSavingsTable(savingsTransactionSheet,dateFormat);
            setRules(savingsTransactionSheet,dateFormat);
            setDefaults(savingsTransactionSheet);
        }

}
