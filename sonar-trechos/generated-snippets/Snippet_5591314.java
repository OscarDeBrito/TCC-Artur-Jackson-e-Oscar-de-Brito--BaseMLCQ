public class Snippet__5591314 {

    @Test
        public void testHtml() throws Exception
        {
            HSSFWorkbook workbook;
            try {
                workbook = ExcelToHtmlUtils.loadXls( child );
            } catch ( Exception exc ) {
                // unable to parse file -- not ExcelToFoConverter fault
                return;
            }

            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                    XMLHelper.getDocumentBuilderFactory().newDocumentBuilder().newDocument() );
            excelToHtmlConverter.processWorkbook( workbook );

            StringWriter stringWriter = new StringWriter();

            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
            transformer.setOutputProperty( OutputKeys.INDENT, "no" );
            transformer.setOutputProperty( OutputKeys.METHOD, "html" );
            transformer.transform(
                    new DOMSource( excelToHtmlConverter.getDocument() ),
                    new StreamResult( stringWriter ) );

            assertNotNull(stringWriter.toString());
        }

}
