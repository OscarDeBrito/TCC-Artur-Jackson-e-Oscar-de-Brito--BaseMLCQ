@FunctionTemplate(name = "convert_fromJSON", scope = FunctionScope.SIMPLE, isRandom = true)
  public static class ConvertFromJsonNullableInput implements DrillSimpleFunc {

    @Param NullableVarBinaryHolder in;
    @Inject DrillBuf buffer;
    @Workspace org.apache.drill.exec.vector.complex.fn.JsonReader jsonReader;

    @Output ComplexWriter writer;

    @Override
    public void setup() {
      jsonReader = new org.apache.drill.exec.vector.complex.fn.JsonReader.Builder(buffer)
          .defaultSchemaPathColumns()
          .build();
    }

    @Override
    public void eval() {
      if (in.isSet == 0) {
        // Return empty map
        org.apache.drill.exec.vector.complex.writer.BaseWriter.MapWriter mapWriter = writer.rootAsMap();
        mapWriter.start();
        mapWriter.end();
        return;
      }

      try {
        jsonReader.setSource(in.start, in.end, in.buffer);
        jsonReader.write(writer);
        buffer = jsonReader.getWorkBuf();
      } catch (Exception e) {
        throw new org.apache.drill.common.exceptions.DrillRuntimeException("Error while converting from JSON. ", e);
      }
    }
  }
