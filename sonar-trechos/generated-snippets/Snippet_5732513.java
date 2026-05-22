public class Snippet__5732513 {

    @Description(
        functionName = "fail",
        description = "Cause some error if the second parameter value is greater than the first parameter",
        example = "> SELECT fail(1, col1, 'message');\n"
            + "1",
        returnType = Type.INT4,
        paramTypes = {
            @ParamTypes(paramTypes = {Type.INT4, Type.INT4, Type.TEXT}),
        }
    )
    public class FailFunction extends GeneralFunction {

      public FailFunction() {
        super(new Column[]{
            new Column("trigger", TajoDataTypes.Type.INT4),
            new Column("input_number", TajoDataTypes.Type.INT4),
            new Column("message", TajoDataTypes.Type.TEXT)
        });
      }

      @Override
      public Datum eval(Tuple params) {

        // to skip the plannin phase
        if (params.isBlankOrNull(0) && params.isBlankOrNull(1)) {
          return DatumFactory.createInt4(params.getInt4(0));
        }

        final int trigger = params.getInt4(0);
        final int num = params.getInt4(1);
        final String msg = params.getText(2);

        if (num >= trigger) {
          throw new TajoInternalError(msg);
        }

        return DatumFactory.createInt4(params.getInt4(0));
      }
    }

}
