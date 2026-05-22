@FunctionTemplate(name = "round", scope = FunctionScope.SIMPLE, nulls = NullHandling.NULL_IF_NULL)
  public static class RoundUInt1 implements DrillSimpleFunc {

    @Param UInt1Holder in;
    @Output UInt1Holder out;

    public void setup() {
    }

    public void eval() {
      out.value = in.value;
    }
  }
