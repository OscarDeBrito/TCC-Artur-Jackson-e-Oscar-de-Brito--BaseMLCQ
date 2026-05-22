public abstract class AMQMethodBodyImpl implements AMQMethodBody
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AMQMethodBodyImpl.class);
    public static final byte TYPE = 1;

    public AMQMethodBodyImpl()
    {
    }

    @Override
    public byte getFrameType()
    {
        return TYPE;
    }


    /** unsigned short
     *
     * @return body size*/
    abstract protected int getBodySize();


    @Override
    public AMQFrame generateFrame(int channelId)
    {
        return new AMQFrame(channelId, this);
    }

    /**
     * Creates an AMQChannelException for the corresponding body type (a channel exception should include the class and
     * method ids of the body it resulted from).
     */

    @Override
    public void handle(final int channelId, final AMQVersionAwareProtocolSession session) throws QpidException
    {
        session.methodFrameReceived(channelId, this);
    }

    @Override
    public int getSize()
    {
        return 2 + 2 + getBodySize();
    }

    @Override
    public long writePayload(final ByteBufferSender sender)
    {

        final int size = getSize();
        try (QpidByteBuffer buf = QpidByteBuffer.allocate(sender.isDirectBufferPreferred(), size))
        {
            buf.putUnsignedShort(getClazz());
            buf.putUnsignedShort(getMethod());
            writeMethodPayload(buf);
            buf.flip();
            sender.send(buf);
        }
        return size;
    }

    abstract protected void writeMethodPayload(QpidByteBuffer buffer);


    protected int getSizeOf(AMQShortString string)
    {
        return EncodingUtils.encodedShortStringLength(string);
    }

    protected void writeByte(QpidByteBuffer buffer, byte b)
    {
        buffer.put(b);
    }

    protected void writeAMQShortString(QpidByteBuffer buffer, AMQShortString string)
    {
        EncodingUtils.writeShortStringBytes(buffer, string);
    }


    protected void writeInt(QpidByteBuffer buffer, int i)
    {
        buffer.putInt(i);
    }


    protected int getSizeOf(FieldTable table)
    {
        return EncodingUtils.encodedFieldTableLength(table);  //To change body of created methods use File | Settings | File Templates.
    }

    protected void writeFieldTable(QpidByteBuffer buffer, FieldTable table)
    {
        EncodingUtils.writeFieldTableBytes(buffer, table);
    }

    protected void writeLong(QpidByteBuffer buffer, long l)
    {
        buffer.putLong(l);
    }


    protected int getSizeOf(byte[] response)
    {
        return (response == null) ? 4 : response.length + 4;
    }

    protected void writeBytes(QpidByteBuffer buffer, byte[] data)
    {
        EncodingUtils.writeBytes(buffer,data);
    }

    protected void writeShort(QpidByteBuffer buffer, short s)
    {
        buffer.putShort(s);
    }

    protected void writeBitfield(QpidByteBuffer buffer, byte bitfield0)
    {
        buffer.put(bitfield0);
    }

    protected void writeUnsignedShort(QpidByteBuffer buffer, int s)
    {
        buffer.putUnsignedShort(s);
    }

    protected void writeUnsignedInteger(QpidByteBuffer buffer, long i)
    {
        buffer.putUnsignedInt(i);
    }

    protected void writeUnsignedByte(QpidByteBuffer buffer, short unsignedByte)
    {
        buffer.putUnsignedByte(unsignedByte);
    }

}
