public interface MapJoinRowContainer extends AbstractRowContainer<List<Object>> {

  byte getAliasFilter() throws HiveException;

  MapJoinRowContainer copy() throws HiveException;

  void addRow(Object[] value) throws HiveException;

  void write(MapJoinObjectSerDeContext valueContext, ObjectOutputStream out)
      throws IOException, SerDeException;
}
