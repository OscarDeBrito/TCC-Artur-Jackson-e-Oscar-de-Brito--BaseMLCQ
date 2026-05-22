public static class C extends B {
    public int fC = 1;

    @JsConstructor
    public C(int x) {
      super(x * 2); // must call super(int), cannot call super().
      this.fC = 6;
    }

    public C(int x, int y) {
      this(x + y); // must call this(int);
      this.fC = 7;
    }
  }
