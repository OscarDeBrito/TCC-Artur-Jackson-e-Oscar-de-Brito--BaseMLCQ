public class Snippet__5395652 {

    public static Schema newBagSchema(String bagName, String tupleName,
                List<String> fieldNames, List<Byte> dataTypes)
                throws FrontendException {
            checkParameters(fieldNames, dataTypes);

            Schema tupleSchema = newTupleSchema(tupleName, fieldNames, dataTypes);
            Schema.FieldSchema bagField = new Schema.FieldSchema(bagName,
                    tupleSchema, DataType.BAG);

            return new Schema(bagField);
        }

}
