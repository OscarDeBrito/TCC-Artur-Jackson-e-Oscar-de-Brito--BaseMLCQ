private static class SortedNumericDocValuesSub extends DocIDMerger.Sub {

    final SortedNumericDocValues values;

    public SortedNumericDocValuesSub(MergeState.DocMap docMap, SortedNumericDocValues values) {
      super(docMap);
      this.values = values;
      assert values.docID() == -1;
    }

    @Override
    public int nextDoc() throws IOException {
      return values.nextDoc();
    }
  }
