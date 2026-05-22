public class Snippet__8969767 {

    public void testIndependantIterators() throws IOException {
        Directory dir = newDirectory();
        IndexWriterConfig conf = newIndexWriterConfig().setMergePolicy(newLogMergePolicy());
        CannedNormSimilarity sim = new CannedNormSimilarity(new long[] {42, 10, 20});
        conf.setSimilarity(sim);
        RandomIndexWriter writer = new RandomIndexWriter(random(), dir, conf);
        Document doc = new Document();
        Field indexedField = new TextField("indexed", "a", Field.Store.NO);
        doc.add(indexedField);
        for (int i = 0; i < 3; ++i) {
          writer.addDocument(doc);
        }
        writer.forceMerge(1);
        LeafReader r = getOnlyLeafReader(maybeWrapWithMergingReader(writer.getReader()));
        NumericDocValues n1 = r.getNormValues("indexed");
        NumericDocValues n2 = r.getNormValues("indexed");
        assertEquals(0, n1.nextDoc());
        assertEquals(42, n1.longValue());
        assertEquals(1, n1.nextDoc());
        assertEquals(10, n1.longValue());
        assertEquals(0, n2.nextDoc());
        assertEquals(42, n2.longValue());
        assertEquals(1, n2.nextDoc());
        assertEquals(10, n2.longValue());
        assertEquals(2, n2.nextDoc());
        assertEquals(20, n2.longValue());
        assertEquals(2, n1.nextDoc());
        assertEquals(20, n1.longValue());
        assertEquals(DocIdSetIterator.NO_MORE_DOCS, n1.nextDoc());
        assertEquals(DocIdSetIterator.NO_MORE_DOCS, n2.nextDoc());
        IOUtils.close(r, writer, dir);
      }

}
