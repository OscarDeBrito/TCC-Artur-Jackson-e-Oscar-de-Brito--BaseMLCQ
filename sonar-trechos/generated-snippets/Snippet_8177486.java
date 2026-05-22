public class Snippet__8177486 {

    protected void looseMarshalByteArray(OpenWireFormat wireFormat, byte[] data, DataOutput dataOut)
            throws IOException {
            dataOut.writeBoolean(data != null);
            if (data != null) {
                dataOut.writeInt(data.length);
                dataOut.write(data);
            }
        }

}
