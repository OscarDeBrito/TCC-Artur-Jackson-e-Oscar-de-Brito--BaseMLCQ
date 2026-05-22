static class SimpleBinaryLoader implements Loader {

    private static final Bytes ROW = Bytes.of(new byte[] {'r', '1', 13});

    @Override
    public void load(TransactionBase tx, Context context) throws Exception {
      TestUtil.increment(tx, ROW, new Column(Bytes.of("a"), Bytes.of(new byte[] {0, 9})), 1);
    }
  }
