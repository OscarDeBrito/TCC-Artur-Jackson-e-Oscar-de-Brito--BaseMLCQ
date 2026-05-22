public class Snippet__8971411 {

    public void testSortedSetEnumAdvanceIndependently() throws IOException {
        Directory directory = newDirectory();
        Analyzer analyzer = new MockAnalyzer(random());
        IndexWriterConfig iwconfig = newIndexWriterConfig(analyzer);
        iwconfig.setMergePolicy(newLogMergePolicy());
        RandomIndexWriter iwriter = new RandomIndexWriter(random(), directory, iwconfig);
    
        Document doc = new Document();
        SortedSetDocValuesField field1 = new SortedSetDocValuesField("field", new BytesRef("2"));
        SortedSetDocValuesField field2 = new SortedSetDocValuesField("field", new BytesRef("3"));
        doc.add(field1);
        doc.add(field2);
        iwriter.addDocument(doc);
        field1.setBytesValue(new BytesRef("1"));
        iwriter.addDocument(doc);
        field2.setBytesValue(new BytesRef("2"));
        iwriter.addDocument(doc);

        iwriter.commit();
        iwriter.forceMerge(1);

        DirectoryReader ireader = iwriter.getReader();
        iwriter.close();

        SortedSetDocValues dv = getOnlyLeafReader(ireader).getSortedSetDocValues("field");
        doTestSortedSetEnumAdvanceIndependently(dv);

        ireader.close();
        directory.close();
      }

}
