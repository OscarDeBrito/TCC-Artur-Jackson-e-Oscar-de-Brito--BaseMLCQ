public class Snippet__7700031 {

    public void write(short[] shorts) {
            ensureBufferSize(shorts.length * SizeOf.USHORT);
            for (short s : shorts) {
                writeShort(s);
            }
            if (this.data.position() > this.dataBound) {
                this.dataBound = this.data.position();
            }
        }

}
