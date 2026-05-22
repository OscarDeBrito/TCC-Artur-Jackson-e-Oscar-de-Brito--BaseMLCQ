public static class CrunchAvroWriteSupport extends AvroWriteSupport {
    @Override
    public WriteContext init(Configuration conf) {
      String outputName = conf.get("crunch.namedoutput");
      if (outputName != null && !outputName.isEmpty()) {
        String schema = conf.get(PARQUET_AVRO_SCHEMA_PARAMETER + "." + outputName);
        setSchema(conf, new Schema.Parser().parse(schema));
      }
      return super.init(conf);
    }
  }
