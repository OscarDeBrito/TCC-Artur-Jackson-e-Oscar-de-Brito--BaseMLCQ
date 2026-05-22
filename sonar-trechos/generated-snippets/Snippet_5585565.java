public class Snippet__5585565 {

    public void testGetColumnWidthString() throws IOException {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("sheet");
            Row row = sheet.createRow(0);
            sheet.createRow(1);
            sheet.createRow(2);
            Cell cell = row.createCell(0);
        
            cell.setCellValue("sometext");
        
            assertTrue("Having some width for rows with actual cells", 
                    SheetUtil.getColumnWidth(sheet, 0, true) > 0);
            assertEquals("Not having any widht for rows with all empty cells", 
                    -1.0, SheetUtil.getColumnWidth(sheet, 0, true, 1, 2));
        
            wb.close();
        }

}
