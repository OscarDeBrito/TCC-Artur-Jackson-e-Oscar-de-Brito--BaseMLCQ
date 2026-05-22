public class EndStreamTuple extends WindowIdTuple
{
  public static byte[] getSerializedTuple(int windowId)
  {
    byte[] serializedTuple = WindowIdTuple.getSerializedTuple(windowId);
    serializedTuple[0] = MessageType.END_STREAM_VALUE;
    return serializedTuple;
  }

  public EndStreamTuple(byte[] array, int offset, int length)
  {
    super(array, offset, length);
  }

}
