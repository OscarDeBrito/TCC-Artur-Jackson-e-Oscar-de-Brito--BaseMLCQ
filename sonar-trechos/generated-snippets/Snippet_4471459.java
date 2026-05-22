@FunctionTemplate(names = {"hash64", "hash64AsDouble"}, scope = FunctionScope.SIMPLE, nulls = FunctionTemplate.NullHandling.INTERNAL)
  public static class TimeHash implements DrillSimpleFunc {
    @Param  TimeHolder in;
    @Param BigIntHolder seed;
    @Output BigIntHolder out;


    public void setup() {
    }

    public void eval() {
      out.value = org.apache.drill.exec.expr.fn.impl.HashHelper.hash64(in.value, seed.value);
    }
  }
