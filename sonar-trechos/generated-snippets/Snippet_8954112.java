public class ResultWritable implements Writable {

  private Result result;

  public ResultWritable() {

  }
  public ResultWritable(Result result) {
    this.result = result;
  }

  public Result getResult() {
    return result;
  }
  public void setResult(Result result) {
    this.result = result;
  }
  @Override
  public void readFields(final DataInput in)
  throws IOException {
    ClientProtos.Result protosResult = ClientProtos.Result.parseDelimitedFrom(DataInputInputStream.from(in));
    int size = in.readInt();
    if(size < 0) {
      throw new IOException("Invalid size " + size);
    }
    Cell[] kvs = new Cell[size];
    for (int i = 0; i < kvs.length; i++) {
      kvs[i] = KeyValue.create(in);
    }
    result = ProtobufUtil.toResult(protosResult, CellUtil.createCellScanner(kvs));
  }
  @Override
  public void write(final DataOutput out)
  throws IOException {
    ProtobufUtil.toResultNoData(result).writeDelimitedTo(DataOutputOutputStream.from(out));
    out.writeInt(result.size());
    for(Cell cell : result.listCells()) {
      KeyValue kv = KeyValueUtil.ensureKeyValue(cell);
      KeyValue.write(kv, out);
    }
  }
}
