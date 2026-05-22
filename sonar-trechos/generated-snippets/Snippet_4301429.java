public class Snippet__4301429 {

    protected void toBytes() {
            byte[] bytes = ByteBuffer.allocate(4).putInt(getValue().getValue()).array();
            setBytes(bytes);
        }

}
