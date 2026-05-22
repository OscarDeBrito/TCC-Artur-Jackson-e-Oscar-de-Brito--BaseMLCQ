static class Packed64SingleBlock6 extends Packed64SingleBlock {

    Packed64SingleBlock6(int valueCount) {
      super(valueCount, 6);
    }

    @Override
    public long get(int index) {
      final int o = index / 10;
      final int b = index % 10;
      final int shift = b * 6;
      return (blocks[o] >>> shift) & 63L;
    }

    @Override
    public void set(int index, long value) {
      final int o = index / 10;
      final int b = index % 10;
      final int shift = b * 6;
      blocks[o] = (blocks[o] & ~(63L << shift)) | (value << shift);
    }

  }
