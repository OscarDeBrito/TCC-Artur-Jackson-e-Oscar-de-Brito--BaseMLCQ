public final class ConstantInteger extends Constant implements ConstantObject {

    private int bytes;


    /**
     * @param bytes Data
     */
    public ConstantInteger(final int bytes) {
        super(Const.CONSTANT_Integer);
        this.bytes = bytes;
    }


    /**
     * Initialize from another object.
     */
    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantInteger(final DataInput file) throws IOException {
        this(file.readInt());
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitConstantInteger(this);
    }


    /**
     * Dump constant integer to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public final void dump( final DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeInt(bytes);
    }


    /**
     * @return data, i.e., 4 bytes.
     */
    public final int getBytes() {
        return bytes;
    }


    /**
     * @param bytes the raw bytes that represent this integer
     */
    public final void setBytes( final int bytes ) {
        this.bytes = bytes;
    }


    /**
     * @return String representation.
     */
    @Override
    public final String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }


    /** @return Integer object
     */
    @Override
    public Object getConstantValue( final ConstantPool cp ) {
        return Integer.valueOf(bytes);
    }
}
