public class Snippet__5604954 {

    @Override
        public XSSFRichTextString createRichTextString(String text) {
            XSSFRichTextString rt = new XSSFRichTextString(text);
            rt.setStylesTableReference(workbook.getStylesSource());
            return rt;
        }

}
