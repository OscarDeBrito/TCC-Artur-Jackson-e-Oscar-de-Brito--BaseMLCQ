private static class DoubleNotPackedWriters extends AbstractPrimitiveWriters<double[], Double> {
    public DoubleNotPackedWriters(Field protoField) {
      super(protoField);

      primitiveArrayWriter = (OutputEx output, double[] array) -> {
        for (double element : array) {
          output.writeDouble(tag, tagSize, element);
        }
      };

      arrayWriter = (OutputEx output, Double[] array) -> {
        for (Double element : array) {
          if (element != null) {
            output.writeDouble(tag, tagSize, element);
            continue;
          }

          ProtoUtils.throwNotSupportNullElement(protoField);
        }
      };

      collectionWriter = (OutputEx output, Collection<Double> collection) -> {
        for (Double element : collection) {
          if (element != null) {
            output.writeDouble(tag, tagSize, element);
            continue;
          }

          ProtoUtils.throwNotSupportNullElement(protoField);
        }
      };

      stringArrayWriter = (OutputEx output, String[] array) -> {
        for (String element : array) {
          if (element != null) {
            double parsedValue = Double.parseDouble(element);
            output.writeDouble(tag, tagSize, parsedValue);
            continue;
          }

          ProtoUtils.throwNotSupportNullElement(protoField);
        }
      };
    }
  }
